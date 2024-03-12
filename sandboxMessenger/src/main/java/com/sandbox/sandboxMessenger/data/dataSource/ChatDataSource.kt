package com.sandbox.sandboxMessenger.data.dataSource

import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import net.folivo.trixnity.clientserverapi.client.SyncApiClientImpl
import net.folivo.trixnity.clientserverapi.client.UIA
import net.folivo.trixnity.clientserverapi.model.authentication.Login
import net.folivo.trixnity.clientserverapi.model.authentication.Register
import net.folivo.trixnity.clientserverapi.model.authentication.WhoAmI
import net.folivo.trixnity.clientserverapi.model.media.Media
import net.folivo.trixnity.clientserverapi.model.users.GetProfile
import net.folivo.trixnity.core.model.RoomAliasId
import net.folivo.trixnity.core.model.RoomId
import net.folivo.trixnity.core.model.UserId

interface ChatDataSource {


    suspend fun getUserId(): Result<WhoAmI.Response>
    suspend fun getAccessToken(): String?

    suspend fun loginUserPass(user: String, pass: String, deviceId: String?): Result<Login.Response>

    suspend fun loginToken(token: String)

    suspend fun registerUserPass(user: String, pass: String, deviceId: String?): Result<UIA<Register.Response>>


    suspend fun createChatRoom(roomName: String,userId: UserId): Result<RoomId>
    suspend fun joinChatRoom(roomAlias: RoomAliasId): Result<RoomId>

    suspend fun getProfile(userId: UserId): Result<GetProfile.Response>

    suspend fun inviteUser(roomId: RoomId, tagetUserName: UserId): MatrixClientServerApiClientImpl?

    suspend fun sendMessage(roomId: RoomId,text: String)


    suspend fun getImageUrl(mxc:String): Result<Media>

    suspend fun getMatrix(): MatrixClientServerApiClientImpl

    suspend fun syncMatrix(): SyncApiClientImpl



}