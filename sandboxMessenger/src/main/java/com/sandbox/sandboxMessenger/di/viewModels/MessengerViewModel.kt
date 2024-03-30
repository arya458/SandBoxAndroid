package com.sandbox.sandboxMessenger.di.viewModels

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandbox.sandboxMessenger.MessageSync
import com.sandbox.sandboxMessenger.repository.MessengerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.util.toByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.client.UIA
import net.folivo.trixnity.clientserverapi.model.authentication.Login
import net.folivo.trixnity.clientserverapi.model.authentication.Register
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.UserId
import net.folivo.trixnity.core.model.events.ClientEvent
import net.folivo.trixnity.core.model.events.MessageEventContent
import net.folivo.trixnity.core.subscribeContent
import javax.inject.Inject


@HiltViewModel
class MessengerViewModel @Inject constructor(
        private val messengerRepository: MessengerRepository,
) : ViewModel() {

    private val _supportProfile: MutableStateFlow<GetProfile.Response> = MutableStateFlow(GetProfile.Response("", ""))
    val supportProfile: StateFlow<GetProfile.Response> = _supportProfile


    private val _registerStage: MutableStateFlow<UIA<Register.Response>?> = MutableStateFlow(null)
    val registerStage: StateFlow<UIA<Register.Response>?> = _registerStage

    private val _loginStage: MutableStateFlow<Result<Login.Response>?> = MutableStateFlow(null)
    val loginStage: StateFlow<Result<Login.Response>?> = _loginStage

    private val _userProfile: MutableStateFlow<GetProfile.Response> = MutableStateFlow(GetProfile.Response("", ""))
    val userProfile: StateFlow<GetProfile.Response> = _userProfile

    private val _supportProfileImage: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val supportProfileImage: StateFlow<Bitmap?> = _supportProfileImage

    private val _userProfileImage: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val userProfileImage: StateFlow<Bitmap?> = _userProfileImage

    val isLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    var userInfo: UserId? = null

//    val messagesList: StateFlow<SnapshotStateList<RoomMessageEventContent>> = messengerRepository.messagesList

    var matrix: MatrixClientServerApiClientImpl? = null

    init {
        getSupportProfile()
        getUserProfile()
        getMatrix()
    }


    fun stat() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = messengerRepository.getUserFromDataBase()
            if (data != null) {
                if (data.token != "") {
                    val res = messengerRepository.loginWithToken()
                    if (res == null)
                        isLoggedIn.value = false
                    else {
                        isLoggedIn.value = true
                        getUserProfile()
                    }
                }
            } else
                isLoggedIn.value = false
            Log.d("fatal", "stat: ${isLoggedIn.value}")

        }
    }

    fun register(user: String, pass: String, deviceId: String?) {
        viewModelScope.launch {

            val response = messengerRepository.registerWithPass(user, pass, deviceId)
            _registerStage.value = response
            when (response) {
                is UIA.Error -> {}
                is UIA.Step -> {}
                is UIA.Success -> {

                    val data = (response as UIA.Success)
                    if (data.value.accessToken != null)
                        loginNewUserToken(data.value.userId.full, data.value.accessToken!!)
                    Log.d("testMatrix", "register: ${data.value}")

                }

                null -> {}
            }

        }
    }


//    fun emailVerify(session: String, email: String) {
//        viewModelScope.launch {
//            _verifyRegisterStage.value =  messengerRepository.verifyingUserEmailAddress(session,email)
//        }
//    }

    fun login(user: String, pass: String, deviceId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginStage.value = messengerRepository.loginWithPass(user, pass, deviceId).onSuccess {
                getUserProfile()
            }
            Log.d("testMatrix", "login: ${_loginStage.value}")
        }
    }

    private fun loginNewUserToken(user: String, token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messengerRepository.loginNewUserWithToken(user, token)
        }
    }

    fun loginToken() {
        viewModelScope.launch(Dispatchers.IO) {
            messengerRepository.loginWithToken()
        }
    }


//    fun isLoggedIn(): Boolean {
//        return user != null
//    }

    fun makeChatRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = messengerRepository.makeChatRoom()
            Log.d("testMatrix", "makeChatRoom: ${res.isFailure}")

        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messengerRepository.sendMessage(text)
        }
    }

    private fun getSupportProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val userProfile = messengerRepository.getSupportProfile().getOrNull()
            if (userProfile != null)
                if (userProfile.avatarUrl != null) {
                    val imageData = getImageUrl(userProfile.avatarUrl!!).getOrNull()
                    if (imageData != null) {
                        val array = imageData.content.toByteArray()
                        _supportProfileImage.value = BitmapFactory.decodeByteArray(array, 0, array.size)
                        _supportProfile.value = userProfile
                    }
                }
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            startService()
            messengerRepository.getUserProfile().onSuccess {
                if (it.avatarUrl != null) {
                    val imageData = getImageUrl(it.avatarUrl!!).getOrNull()
                    if (imageData != null) {
                        val array = imageData.content.toByteArray()
                        _userProfileImage.value = BitmapFactory.decodeByteArray(array, 0, array.size)
                    }
                } else {
                    //todo:: Place Holder for No Image
                }
                _userProfile.value = it
                Log.d("fatal", "messengerRepository Success: ${it.displayName}")

            }.onFailure {
                Log.d("fatal", "messengerRepository Failure: ${it.message}")

            }

            Log.d("fatal", "userProfile: ${userProfile.value}")
            Log.d("fatal", "userProfileImage: ${userProfileImage.value}")
        }
    }

    private suspend fun getImageUrl(avatarUrl: String): Result<Media> {
        Log.d("testMatrix", "getImageUrl: $avatarUrl")
        return messengerRepository.getImageUrl(avatarUrl)
    }

    private fun getMatrix() {
        viewModelScope.launch(Dispatchers.IO) {
            matrix = messengerRepository.getMatrix()
            matrix!!.sync.subscribeContent<MessageEventContent>() {
                Log.d("testMatrix", "Message : ${it.content}")
            }
        }
    }

//    private fun liveMessageList() {
//
//        Log.d("testMatrix", "subscribeContent: Started")
//
////        chatRepository.setLiveMessages(_messagesList)
//        messengerRepository.getMatrix().sync.subscribeContent<MessageEventContent>() {
//            val raw: ClientEvent<MessageEventContent> = it
//            Log.d("testMatrix", "Message : ${it.content}")
//            if (raw.senderOrNull == messengerRepository.getSupportUserId() || raw.senderOrNull == userInfo)
//                if (raw.roomIdOrNull == messengerRepository.getRoomId()) {
////                    println(raw.senderOrNull)
////                    println(raw.roomIdOrNull)
////            Log.d("testMatrix", "subscribeContent: $raw")
//
//                    val currentData = _messagesList.value
//                    if (!_messagesList.value.contains(raw.content)) {
//                        val messageEvent: MessageEventContent = raw.content
//                        val messageContent: RoomMessageEventContent = (messageEvent as RoomMessageEventContent)
//                        currentData.add(messageContent)
//                        _messagesList.value = currentData
//                        Log.d("testMatrix", "subscribeContent: $messageContent")
//                        Log.d("testMatrix", "subscribeContent Array Size: ${_messagesList.value.size}")
//                    }
//                }
//
//        }
//    }

//    private fun startListeningForMessages() {
//        viewModelScope.launch(Dispatchers.IO) {
//            liveMessageList()
//
//            matrix!!.sync.sync(since = null).onSuccess { res ->
//
//                nextBatch.value = res.nextBatch
//            }
//
//            Log.d("testMatrix", "startLiveMessageList: ${nextBatch.value}")
//
//            matrix!!.sync.start(
//                    setBatchToken = { nextBatch.value = it },
//                    scope = CoroutineScope(this.coroutineContext),
//                    wait = false,
//                    getBatchToken = { if (nextBatch.value == "") null else nextBatch.value },
//                    withTransaction = {})
//            Log.d("testMatrix", "subscribeContent: ${matrix!!.sync.currentSyncState.value}")
//            delay(4.minutes) // wait some time
//            matrix!!.sync.stop()
//            Log.d("testMatrix", "subscribeContent: ${matrix!!.sync.currentSyncState.value}")
//
//        }
//    }

    fun getMessageList(): MutableStateFlow<SnapshotStateList<ClientEvent<MessageEventContent>>> {
        return messengerRepository.messagesList
    }


    fun startService() {
        val context = messengerRepository.getContext()
//        liveMessageList()
        val intent: Intent = Intent(context, MessageSync::class.java)
        context.startService(intent)
    }

    fun stopService() {
        val context = messengerRepository.getContext()
        val intent: Intent = Intent(context, MessageSync::class.java)
        context.stopService(intent)
    }

}
