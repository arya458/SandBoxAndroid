package com.sandbox.sandboxMessenger.data.dataSource

import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.client.SyncApiClientImpl
import net.folivo.trixnity.clientserverapi.client.UIA
import net.folivo.trixnity.clientserverapi.model.authentication.AccountType
import javax.inject.Inject
import net.folivo.trixnity.clientserverapi.model.authentication.*
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.RoomAliasId
import net.folivo.trixnity.core.model.RoomId
import net.folivo.trixnity.core.model.UserId
import net.folivo.trixnity.core.model.events.m.room.RoomMessageEventContent

class ChatDataSourceImpl @Inject constructor(
        private val matrix: MatrixClientServerApiClientImpl,
) : ChatDataSource {



    override suspend fun getUserId(): Result<WhoAmI.Response> {
        return matrix.authentication.whoAmI()
    }

    override suspend fun getAccessToken(): String? {
        return matrix.accessToken.value
    }

    override suspend fun loginToken(token: String) {
        matrix.accessToken.value = token
    }

    override suspend fun loginUserPass(user: String, pass: String, deviceId: String?): Result<Login.Response> {
        return matrix.authentication.login(

                identifier = IdentifierType.User(user),
                password = pass,
                type = LoginType.Password,
                deviceId = deviceId,
                initialDeviceDisplayName = "SandBox"
        )

    }

    override suspend fun registerUserPass(user: String, pass: String, deviceId: String?): Result<UIA<Register.Response>> {
        return matrix.authentication.register(username = user,
                password = pass,
                accountType = AccountType.USER,
                deviceId = deviceId,
                initialDeviceDisplayName = "SandBox",
                inhibitLogin = true,
                isAppservice = false
        )
    }


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

    override suspend fun sendMessage(roomId: RoomId, text: String) {
        matrix.room.sendMessageEvent(
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

    override suspend fun getMatrix(): MatrixClientServerApiClientImpl {
        return matrix

    }

    override suspend fun syncMatrix(): SyncApiClientImpl {
        return matrix.sync
    }


}