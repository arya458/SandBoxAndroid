package com.sandbox.sandboxMessenger.data.dataSource

import android.app.Application
import android.util.Log
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.client.SyncApiClientImpl
import net.folivo.trixnity.clientserverapi.client.UIA
import net.folivo.trixnity.clientserverapi.model.authentication.AccountType
import javax.inject.Inject
import net.folivo.trixnity.clientserverapi.model.authentication.*
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.EventId
import net.folivo.trixnity.core.model.RoomAliasId
import net.folivo.trixnity.core.model.RoomId
import net.folivo.trixnity.core.model.UserId
import net.folivo.trixnity.core.model.events.m.room.RoomMessageEventContent

class MessengerDataSourceImpl @Inject constructor(
        private val matrix: MatrixClientServerApiClientImpl,
        private val context : Application
) : MessengerDataSource {

    override fun getContext(): Application {
        return context
    }


    override suspend fun getUserId(): Result<WhoAmI.Response> {
        return matrix.authentication.whoAmI()
    }

    override suspend fun getAccessToken(): String? {
        return matrix.accessToken.value
    }

    override suspend fun loginUserToken(user: String, token: String): Result<WhoAmI.Response> {

        matrix.accessToken.value = token
        return matrix.authentication.whoAmI()

//
//        val res = matrix.authentication.login(
//                identifier = IdentifierType.User(user),
//                token = token,
//                type = LoginType.Token(false),
//                initialDeviceDisplayName = "SandBox"
//        ).onSuccess {
//            matrix.accessToken.value = it.accessToken
//        }
//
//        Log.d("testMatrix", "login: ${res}")
//        return res

    }

    override suspend fun loginUserPass(user: String, pass: String, deviceId: String?): Result<Login.Response> {
        return matrix.authentication.login(
                identifier = IdentifierType.User(user),
                password = pass,
                type = LoginType.Password,
                deviceId = deviceId,
                initialDeviceDisplayName = "SandBox"
        ).onSuccess {
            matrix.accessToken.value = it.accessToken
        }

    }

    override suspend fun identifier(): Result<Set<ThirdPartyIdentifier>> {
        return matrix.authentication.getThirdPartyIdentifiers()
    }

    override suspend fun registerUserPass(user: String, pass: String, deviceId: String?): Result<UIA<Register.Response>> {
        return matrix.authentication.register(
                username = user,
                password = pass,
                accountType = AccountType.USER,
                deviceId = deviceId,
                initialDeviceDisplayName = "SandBoxMessenger",
                inhibitLogin = false,
                isAppservice = false
        )
    }

//    override suspend fun registerStepEmail(clientSecret: String,email: String,sendAttempt : Long): Result<GetEmailRequestTokenForRegistration.Response> {
//        return matrix.authentication.getEmailRequestTokenForRegistration(GetEmailRequestTokenForRegistration.Request(clientSecret,email, sendAttempt = sendAttempt))
//    }


    override suspend fun createChatRoom(roomName: String, tagetUserName: UserId): Result<RoomId> {
//        val roomAliasId = RoomAliasId(roomName, "matrix.org")
//        var roomRes: GetRoomAlias.Response?=null
//        matrix.room.getRoomAlias(roomAliasId).onSuccess { roomRes = it }
//        if (roomRes==null)

        return matrix.room.createRoom(
                invite = buildSet { tagetUserName },
                roomAliasId = RoomAliasId("SandBox_$roomName",tagetUserName.domain),
                isDirect = true,
                name = "SandBox_$roomName"
        ).onSuccess {
//                roomRes=it
            println("Room Is Ready To Use")
        }

//        return matrix.room.joinRoom(roomAliasId)
    }

    override suspend fun joinChatRoom(roomAlias: RoomAliasId): Result<RoomId> {
//        val roomAliasId = RoomAliasId(roomName, "matrix.org")
//        var roomRes: GetRoomAlias.Response?=null
//        matrix.room.getRoomAlias(roomAliasId).onSuccess { roomRes = it }
//        if (roomRes==null)
//        val roomAliasId = RoomAliasId(roomName, "matrix.org")

//        println(matrixRestClient.room.joinRoom(roomAliasId))
//        val roomAlias = roomAliases?.first()
        return matrix.room.joinRoom(roomAlias)


//        return matrix.room.joinRoom(roomAliasId)
    }


    override suspend fun getProfile(userId: UserId): Result<GetProfile.Response> {
        return matrix.user.getProfile(userId)
    }

    override suspend fun inviteUser(roomId: RoomId, tagetUserName: UserId): MatrixClientServerApiClientImpl? {
        matrix.room.inviteUser(
                roomId,
                tagetUserName,
                "System : Adding Support Account To Room"
        )
                .onSuccess {
                    return matrix
                }
        return null
    }

    override suspend fun sendMessage(roomId: RoomId, text: String): Result<EventId> {
        return matrix.room.sendMessageEvent(
                roomId,
                RoomMessageEventContent.TextMessageEventContent(
                        text,
                        "m.text"
                )
        )
    }


    override suspend fun getImageUrl(mxc: String): Result<Media> {
        return matrix.media.download(mxc)
    }

    override fun getMatrix(): MatrixClientServerApiClientImpl {
        return matrix

    }

    override suspend fun syncMatrix(): SyncApiClientImpl {
        return matrix.sync
    }


}