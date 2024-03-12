package com.sandbox.sandboxMessenger.repository

import android.util.Log
import com.sandbox.sandboxMessenger.data.dataSource.ChatDataSource
import com.arya.danesh.utilities.CoreUtility.MATRIX_TARGET_DOMAIN
import com.arya.danesh.utilities.CoreUtility.MATRIX_TRAGET_USER
import com.arya.danesh.utilities.CoreUtility.getRandomString
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.model.authentication.WhoAmI
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.RoomAliasId
import net.folivo.trixnity.core.model.RoomId
import net.folivo.trixnity.core.model.UserId
import javax.inject.Inject

class ChatRepository @Inject constructor(
        private val chatDataSource: ChatDataSource
) {


    private var roomId: RoomId? = null

    private var roomAlias: RoomAliasId? = RoomAliasId("SandBox_V3osY075vkZrO2AzArxl", MATRIX_TARGET_DOMAIN)

    //    private var roomAlias: RoomAliasId? = null
    private var supportUserId: UserId = UserId(MATRIX_TRAGET_USER, MATRIX_TARGET_DOMAIN)

    private var whoAmI: WhoAmI.Response? = null


    suspend fun setWhoAmI() {
        if (whoAmI == null) {
            whoAmI = chatDataSource.getUserId().getOrNull()
        }
    }
    suspend fun getWhoAmI(): WhoAmI.Response? {
        if (whoAmI==null)
            setWhoAmI()
        return whoAmI
    }


    suspend fun setUp(token: String) {
        chatDataSource.loginToken(token)
        setWhoAmI()
//        chatDataSource.setupSubscribeContent()
    }

    suspend fun getRoomId(): RoomId? {
        return roomId
    }

    suspend fun getSupportUserId(): UserId {
        return supportUserId
    }

    suspend fun getMatrix(): MatrixClientServerApiClientImpl {
        return chatDataSource.getMatrix()
    }

    suspend fun makeChatRoom(): Result<RoomId> {
        val roomName = getRandomString(20)
        Log.d("testMatrix", "makeChatRoom: ${"SandBox_$roomName"}")

        return if (roomAlias != null) {
            chatDataSource.joinChatRoom(roomAlias!!).onSuccess {
                roomId = it
            }
        } else
            chatDataSource.createChatRoom(roomName, supportUserId)
                    .onSuccess {
                        roomId = it
                        roomAlias = RoomAliasId("SandBox_$roomName", MATRIX_TARGET_DOMAIN)
                        Log.d("testMatrix", "makeChatRoom: $it")
                        chatDataSource.joinChatRoom(roomAlias!!)
                        chatDataSource.inviteUser(roomId!!, supportUserId)
                    }
                    .onFailure {
                        Log.d("testMatrix", "makeChatRoom: $it")
                        roomId = null
                    }
    }

    suspend fun sendMessage(text: String) {
        roomId?.let { chatDataSource.sendMessage(it, text) }
    }

//    suspend fun getSupportProfile(): Result<GetProfile.Response> {
//        return chatDataSource.getProfile(supportUserId)
//    }

    suspend fun getUserProfile(userId: UserId=supportUserId): Result<GetProfile.Response> {
        return chatDataSource.getProfile(userId)
    }

    suspend fun getImageUrl(mxc: String): Result<Media> {
        return chatDataSource.getImageUrl(mxc)
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