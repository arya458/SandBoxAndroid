package com.sandbox.sandboxMessenger

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.sandbox.sandboxMessenger.repository.MessengerRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.folivo.trixnity.clientserverapi.client.SyncApiClient
import net.folivo.trixnity.clientserverapi.client.start
import net.folivo.trixnity.core.model.events.ClientEvent
import net.folivo.trixnity.core.model.events.MessageEventContent
import net.folivo.trixnity.core.model.events.roomIdOrNull
import net.folivo.trixnity.core.model.events.senderOrNull
import net.folivo.trixnity.core.subscribeContent
import javax.inject.Inject


@AndroidEntryPoint
class MessageSync: Service() {

    //    @Inject
//    lateinit var messengerRepository: MessengerRepository

    @Inject
    lateinit var messengerRepository: MessengerRepository


    val nextBatch: MutableStateFlow<String> = MutableStateFlow("")

    //    var matrix: MatrixClientServerApiClient? = messengerRepository?.getMatrix()
//    var sync: SyncApiClient? = matrix?.sync


    override fun onCreate() {
        super.onCreate()
        Log.d("Service", "Started")
        if (this::messengerRepository.isInitialized) {
            val sync: SyncApiClient = messengerRepository.getMatrix().sync
            val userId = messengerRepository.userId.asStateFlow()
            MainScope().launch(Dispatchers.Unconfined) {
                sync.sync(since = null).onSuccess { res ->
                    nextBatch.value = res.nextBatch
                }
                sync.subscribeContent<MessageEventContent> {
                    val raw: ClientEvent<MessageEventContent> = it
                    Log.d("Service", "Message : ${it.content}")
                    Log.d("Service", "Message : ${raw.senderOrNull}")
                    if (raw.senderOrNull == messengerRepository.getSupportUserId() || raw.senderOrNull == userId.value)
                        if (raw.roomIdOrNull == messengerRepository.getRoomId()) {
//                    println(raw.senderOrNull)
//                    println(raw.roomIdOrNull)
//            Log.d("testMatrix", "subscribeContent: $raw")

                            val currentData = messengerRepository.messagesList.value
                            if (!messengerRepository.messagesList.value.contains(raw)) {
                                val messageEvent: ClientEvent<MessageEventContent> = raw
//                                val messageContent: RoomMessageEventContent = (messageEvent as RoomMessageEventContent)
                                currentData.add(messageEvent)
                                messengerRepository.messagesList.value = currentData
//                                Log.d("Service", "subscribeContent: $messageContent")
                                Log.d("Service", "subscribeContent Array Size: ${messengerRepository.messagesList.value.size}")
                            }
                        }
                }

                sync.start(
                        setBatchToken = { nextBatch.value = it },
                        scope = CoroutineScope(this.coroutineContext),
                        wait = false,
                        getBatchToken = { if (nextBatch.value == "") null else nextBatch.value },
                )
                //            delay(1.minutes) // wait some time
                //            apiViewModel.matrix?.sync?.stop()
                Log.d("Service", "Service : Started")
            }
        }

    }

    override fun onDestroy() {
        if (this::messengerRepository.isInitialized) {
            val sync: SyncApiClient = messengerRepository.getMatrix().sync
            Log.d("Service", "Service : Stopped")
            MainScope().launch(Dispatchers.Unconfined) { sync.stop() }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}