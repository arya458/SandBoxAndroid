package com.sandbox.sandboxMessenger.repository

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sandbox.sandboxMessenger.data.dataSource.MessengerDataSource
import com.arya.danesh.utilities.CoreUtility.MATRIX_TARGET_DOMAIN
import com.arya.danesh.utilities.CoreUtility.MATRIX_TRAGET_USER
import com.arya.danesh.utilities.CoreUtility.getRandomString
import com.sandbox.sandboxMessenger.data.dao.MessengerDao
import com.sandbox.sandboxMessenger.data.response.Chat.MessageResponse
import com.sandbox.sandboxMessenger.data.response.MessengerResponse
import dagger.Binds
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.client.UIA
import net.folivo.trixnity.clientserverapi.model.authentication.Login
import net.folivo.trixnity.clientserverapi.model.authentication.Register
import net.folivo.trixnity.clientserverapi.model.authentication.WhoAmI
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.EventId
import net.folivo.trixnity.core.model.RoomAliasId
import net.folivo.trixnity.core.model.RoomId
import net.folivo.trixnity.core.model.UserId
import net.folivo.trixnity.core.model.events.ClientEvent
import net.folivo.trixnity.core.model.events.MessageEventContent
import net.folivo.trixnity.core.model.events.m.room.RoomMessageEventContent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessengerRepository @Inject constructor(
        private val messengerDataSource: MessengerDataSource,
        private val messengerDao: MessengerDao
) {


    private var roomId: RoomId? = null

    //    private var roomAlias: RoomAliasId? = RoomAliasId("SandBox_V3osY075vkZrO2AzArxl", MATRIX_TARGET_DOMAIN)
    private var roomAlias: RoomAliasId? = null

    //    private var roomAlias: RoomAliasId? = null
    private var supportUserId: UserId = UserId(MATRIX_TRAGET_USER, MATRIX_TARGET_DOMAIN)

    val userId: MutableStateFlow<UserId?> = MutableStateFlow(null)
    val messagesList: MutableStateFlow<SnapshotStateList<ClientEvent<MessageEventContent>>> = MutableStateFlow(mutableStateListOf())


//    var isLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(null)


//    suspend fun captcha(): SafetyNetClient {
//        return messengerDataSource.captcha()
//    }

    fun getContext(): Application {
        return messengerDataSource.getContext()
    }

    fun getUserFromDataBase(): MessengerResponse? {
        val data = messengerDao.getData()
        if (data != null)
            return data
        else
            return null
    }

    private suspend fun setToDataBase(
            userId: String = "",
            roomId: String = "",
//            pass: String = "",
            roomAlias: String = "",
            token: String = ""

    ) {
        var lastData = messengerDao.getData()
        if (lastData == null)
            messengerDao.insert(MessengerResponse(userId = userId, roomId = roomId, roomAlias = roomAlias, token = token))
        else {
            lastData.id = 0
            if (lastData.userId == "")
                lastData.userId = userId
            if (lastData.roomAlias == "")
                lastData.roomAlias = roomAlias
//            if (lastData.pass == "")
//                lastData.pass = pass
            if (lastData.roomId == "")
                lastData.roomId = roomId
            if (lastData.token == "")
                lastData.token = token
            messengerDao.update(lastData)
        }
        Log.d("DataBase", "setToDataBase: $lastData")


    }

//    suspend fun getWhoAmI(): Result<WhoAmI.Response> {
//        return messengerDataSource.getUserId().onSuccess {
//            setToDataBase(userId = it.userId.full)
//            isLoggedIn.value = true
//        }
//    }


    suspend fun loginNewUserWithToken(user: String, token: String) {
        messengerDataSource.loginUserToken(user, token).onSuccess {

            setToDataBase(
                    userId = it.userId.full,
                    token = token
            )
            userId.emit(it.userId)


        }
//        getWhoAmI()
    }

    suspend fun loginWithToken(): WhoAmI.Response? {
        val data = getUserFromDataBase()
        data?.let {
            messengerDataSource.loginUserToken(it.userId, it.token).onSuccess { res ->
//                getWhoAmI().onSuccess { a-> a. }

                val token = messengerDataSource.getAccessToken()
                setToDataBase(
                        userId = res.userId.full,
                        token = token ?: ""
                )
                Log.d("testMatrix", "login Token: ${token}")
                Log.d("testMatrix", "login: ${res}")
                userId.emit(res.userId)
                if (it.roomAlias != "")
                    roomAlias = RoomAliasId(it.roomAlias)
                if (it.roomId != "")
                    roomId = RoomId(it.roomId)
                return res
            }.onFailure {
                Log.d("RegisterMatrix", "Login Error : " + it.message)

            }
        }
        return null
    }

    suspend fun loginWithPass(user: String, pass: String, deviceId: String?): Result<Login.Response> {
        return messengerDataSource.loginUserPass(user, pass, deviceId).onSuccess { res ->
            val token = messengerDataSource.getAccessToken()
            setToDataBase(
                    userId = res.userId.full,
                    token = token ?: ""
            )
            userId.emit(res.userId)
            Log.d("testMatrix", "loginWithPass: ${token}")
            Log.d("testMatrix", "loginWithPass: ${res}")


        }.onFailure {
            Log.d("RegisterMatrix", "Login Error : " + it.message)

        }
    }


    suspend fun registerWithPass(user: String, pass: String, deviceId: String?): UIA<Register.Response>? {
        return messengerDataSource.registerUserPass(user, pass, deviceId).getOrNull()
    }


//    suspend fun isLoggedIn(): Boolean {
//        var lastData = messengerDao.getData()
//        if (lastData == null)
//            if (lastData.token != "")
//                return true
//        return false
//    }

    suspend fun getRoomId(): RoomId? {
        val roomFullDomain = messengerDao.getData().roomId
        if (roomFullDomain != "" && roomId == null)
            roomId = RoomId(roomFullDomain)
        return roomId
    }

    suspend fun getSupportUserId(): UserId {
        return supportUserId
    }

    fun getMatrix(): MatrixClientServerApiClientImpl {
        return messengerDataSource.getMatrix()
    }

    suspend fun makeChatRoom(): Result<RoomId> {
        val roomName = getRandomString(20)
        Log.d("testMatrix", "makeChatRoom: ${"SandBox_$roomName"}")
        val data = messengerDao.getData()
        if (data != null) {
            if (data.roomAlias != "")
                roomAlias = RoomAliasId(data.roomAlias)
        }
        return if (roomAlias != null) {
            messengerDataSource.joinChatRoom(roomAlias!!).onSuccess {
                roomId = it
                setToDataBase(roomId = it.full)
            }
        } else
            messengerDataSource.createChatRoom(roomName, supportUserId)
                    .onSuccess {
                        setToDataBase(roomId = it.full)
                        roomId = it
                        roomAlias = RoomAliasId("SandBox_$roomName", MATRIX_TARGET_DOMAIN)
                        setToDataBase(roomAlias = roomAlias!!.full)
                        Log.d("testMatrix", "makeChatRoom: $it")
                        messengerDataSource.joinChatRoom(roomAlias!!)
                        messengerDataSource.inviteUser(roomId!!, supportUserId)
                    }
                    .onFailure {
                        Log.d("testMatrix", "makeChatRoom: $it")
                        roomId = null
                    }
    }

    suspend fun sendMessage(text: String): Result<EventId>? {

        roomId?.let { return messengerDataSource.sendMessage(it, text) }
        return null
    }

//    suspend fun getSupportProfile(): Result<GetProfile.Response> {
//        return chatDataSource.getProfile(supportUserId)
//    }

    suspend fun getSupportProfile(): Result<GetProfile.Response> {
        return messengerDataSource.getProfile(supportUserId)
    }

    suspend fun getUserProfile(): Result<GetProfile.Response> {
        val data = messengerDao.getData()
        if (data != null)
            if (data.userId != "")
                return messengerDataSource.getProfile(UserId(data.userId))

        return Result.failure(Throwable(message = "No DB Data For Profile"))
    }

    suspend fun getImageUrl(mxc: String): Result<Media> {
        return messengerDataSource.getImageUrl(mxc)
    }


//    suspend fun setLiveMessages(messagesList: MutableStateFlow<ArrayList<RoomMessageEventContent>>) {
//        chatDataSource.syncMatrix().subscribeContent<MessageEventContent>() {
//            val raw: ClientEvent<MessageEventContent> = it
//
//            if (raw.senderOrNull == supportUserId && raw.roomIdOrNull == roomId) {
////                    println(raw.senderOrNull)
////                    println(raw.roomIdOrNull)
////            Log.d("testMatrix", "subscribeContent: $raw")
//
//                if (!messagesList.value.contains(raw.content)) {
//                    val messageEvent: MessageEventContent = raw.content
//                    val messageContent: RoomMessageEventContent = (messageEvent as RoomMessageEventContent)
//                    messagesList.value.add(messageContent)
//                    Log.d("testMatrix", "subscribeContent: $messageContent")
//                }
//            }
////                    println(messageContent.relatesTo)
////                    println(messageContent.mentions)
////                    println(messageContent.externalUrl)
//        }
//    }


}