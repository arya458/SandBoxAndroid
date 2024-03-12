package com.sandbox.sandboxMessenger.di.viewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandbox.sandboxMessenger.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.util.toByteArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.model.authentication.WhoAmI
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.events.ClientEvent
import net.folivo.trixnity.core.model.events.MessageEventContent
import net.folivo.trixnity.core.model.events.m.room.RoomMessageEventContent
import net.folivo.trixnity.core.model.events.roomIdOrNull
import net.folivo.trixnity.core.model.events.senderOrNull
import net.folivo.trixnity.core.subscribeContent
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes


@HiltViewModel
class ApiViewModel @Inject constructor(
        private val chatRepository: ChatRepository,

        ) : ViewModel() {

    private val _supportProfile: MutableStateFlow<GetProfile.Response> = MutableStateFlow(GetProfile.Response("", ""))
    private val _userProfile: MutableStateFlow<GetProfile.Response> = MutableStateFlow(GetProfile.Response("", ""))
    private val _supportProfileImage: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    private val _userProfileImage: MutableStateFlow<Bitmap?> = MutableStateFlow(null)


    private val nextBatch: MutableStateFlow<String> = MutableStateFlow("")

    val supportProfile: StateFlow<GetProfile.Response> = _supportProfile
    val userProfile: StateFlow<GetProfile.Response> = _userProfile
    val supportProfileImage: StateFlow<Bitmap?> = _supportProfileImage
    val userProfileImage: StateFlow<Bitmap?> = _userProfileImage
//    val messagesList: LiveData<ArrayList<RoomMessageEventContent>> = _messagesList
//    val messagesList: LiveData<ArrayList<RoomMessageEventContent>>
//        get() = _messagesList

    var userInfo :WhoAmI.Response? = null

    private val _messagesList: MutableStateFlow<SnapshotStateList<RoomMessageEventContent>> = MutableStateFlow(mutableStateListOf())

    val messagesList: StateFlow<SnapshotStateList<RoomMessageEventContent>> = _messagesList
//    val messagesArrayList: ArrayList<RoomMessageEventContent> = ArrayList<RoomMessageEventContent>().also { it.addAll(_messagesList.value) }

    var matrix: MatrixClientServerApiClientImpl? = null

    init {
        getMatrix()
        setUp()
        getSupportProfile()
        getUserInfo()
        getUserProfile()
//                makeChatRoom()
    }

    private fun getUserInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            userInfo = chatRepository.getWhoAmI()

        }
    }

    private fun setUp() {
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.setUp("syt_YXBwX3Rlc3Rlcg_GVFIjvgZEGttrPdndbOC_3WOR8Q")
        }
    }

    fun makeChatRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = chatRepository.makeChatRoom()
            Log.d("testMatrix", "makeChatRoom: ${res.isFailure}")

        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.sendMessage(text)
        }
    }

    private fun getSupportProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.getUserProfile().onSuccess {
                _supportProfile.value = it
                if (it.avatarUrl != null) {
                    val imageData = getImageUrl(it.avatarUrl!!).getOrNull()
                    if (imageData != null) {
                        val array = imageData.content.toByteArray()
                        _supportProfileImage.value = BitmapFactory.decodeByteArray(array, 0, array.size)
                    }
                }
            }
        }
    }
    private fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (userInfo!=null)
            chatRepository.getUserProfile(userInfo!!.userId).onSuccess {
                _userProfile.value = it
                if (it.avatarUrl != null) {
                    val imageData = getImageUrl(it.avatarUrl!!).getOrNull()
                    if (imageData != null) {
                        val array = imageData.content.toByteArray()
                        _userProfileImage.value = BitmapFactory.decodeByteArray(array, 0, array.size)
                    }
                }
            }
        }
    }

    private suspend fun getImageUrl(avatarUrl: String): Result<Media> {
        Log.d("testMatrix", "getImageUrl: $avatarUrl")
        return chatRepository.getImageUrl(avatarUrl)
    }

    private fun getMatrix() {
        viewModelScope.launch(Dispatchers.IO) {
            matrix = chatRepository.getMatrix()
            liveMessageList()
        }
    }

    private suspend fun liveMessageList() {

        Log.d("testMatrix", "subscribeContent: Started")

//        chatRepository.setLiveMessages(_messagesList)
        chatRepository.getMatrix().sync.subscribeContent<MessageEventContent>() {
            val raw: ClientEvent<MessageEventContent> = it

            if (raw.senderOrNull == chatRepository.getSupportUserId() || raw.senderOrNull == userInfo?.userId)
            if (raw.roomIdOrNull == chatRepository.getRoomId()) {
//                    println(raw.senderOrNull)
//                    println(raw.roomIdOrNull)
//            Log.d("testMatrix", "subscribeContent: $raw")

                val currentData = _messagesList.value
                if (!_messagesList.value.contains(raw.content)) {
                    val messageEvent: MessageEventContent = raw.content
                    val messageContent: RoomMessageEventContent = (messageEvent as RoomMessageEventContent)
                    currentData.add(messageContent)
                    _messagesList.value = currentData
                    Log.d("testMatrix", "subscribeContent: $messageContent")
                    Log.d("testMatrix", "subscribeContent Array Size: ${_messagesList.value.size}")
                }
            }

        }

        fun startListeningForMessages() {
            MainScope().launch {
                matrix!!.sync.sync(since = null).onSuccess { res ->

                    nextBatch.value = res.nextBatch
                }

                Log.d("testMatrix", "startLiveMessageList: $nextBatch.value")

                matrix!!.sync.start(
                        setBatchToken = { nextBatch.value = it },
                        scope = CoroutineScope(this.coroutineContext),
                        wait = false,
                        getBatchToken = { if (nextBatch.value == "") null else nextBatch.value },
                        withTransaction = {})
                Log.d("testMatrix", "subscribeContent: ${matrix!!.sync.currentSyncState}")
                delay(1.minutes) // wait some time
                matrix!!.sync.stop()
            }
        }

        fun stopListeningForMessages() {
            MainScope().launch {
                matrix!!.sync.stop(true)
            }
        }


    }
}