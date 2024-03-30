package com.sandbox.sandboxMessenger.ui.pages.messenger

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sandbox.sandboxMessenger.di.viewModels.MessengerViewModel
import com.arya.danesh.controller.route.RootNavigation
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessageView
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessengerBottomBar
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessengerTittleBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessengerPage(
        navigateTo: (RootNavigation) -> Unit,
        messengerViewModel: MessengerViewModel = hiltViewModel()
) {


    val message = rememberSaveable { mutableStateOf("") }
    val supportProfile by messengerViewModel.supportProfile.collectAsState()
    val supportProfileImage by messengerViewModel.supportProfileImage.collectAsState()
    val userProfileImage by messengerViewModel.userProfileImage.collectAsState()
    val lazyState = rememberLazyListState()
    val messageList by messengerViewModel.getMessageList().collectAsState()
    val coroutineScope = rememberCoroutineScope()

//    var lastMessageSender = ""


    messengerViewModel.makeChatRoom()

//    apiViewModel.startListeningForMessages()

//    LaunchedEffect(Unit) {
//        MainScope().launch(Unconfined) {
////            println("Started")
////
////
////            messengerViewModel.matrix?.sync?.sync(since = null)?.onSuccess { res ->
////
////                nextBatch.value = res.nextBatch
////            }
////
////            println(nextBatch.value)
////
////            messengerViewModel.matrix?.sync?.start(
////                    setBatchToken = { nextBatch.value = it },
////                    scope = CoroutineScope(this.coroutineContext),
////                    wait = false,
////                    getBatchToken = { if (nextBatch.value == "") null else nextBatch.value },
////            )
//////            delay(1.minutes) // wait some time
//////            apiViewModel.matrix?.sync?.stop()
////            Log.d("testMatrix", "Service: Ended")
//        }
//    }

//    DisposableEffect(key1 = , effect = )
//    Log.d("testMatrix", "ArrayList: "+messageList.value.size)

    Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MessengerTittleBar(username = supportProfile.displayName.toString(),
                        imageBitmap = supportProfileImage?.asImageBitmap(),
                        isOnline = false,
                        onBackClick = { navigateTo(RootNavigation.Root.MainPage) },
                        userOnClick = {
                            navigateTo(RootNavigation.Root.ProfilePage)
//                            Log.d("Service", "MessengerPage: ${messageList.size}")
//                            Log.d("Service", "MessengerPage: ${messengerViewModel.getMessageList().value.size}")
                        })
            },
            floatingActionButton = {

            },
            bottomBar = {
                MessengerBottomBar(
                        userMessage = message.value,
                        setUserMessage = { text: String -> message.value = text }
                ) {
                    messengerViewModel.sendMessage(message.value)
                    message.value=""
                }
            }

    ) {

        LazyColumn(
                Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding()),
                contentPadding = PaddingValues(top = 10.dp, bottom = 140.dp),
                state = lazyState,
        ) {
            itemsIndexed(messageList) { _, item ->
                MessageView(item, supportImage = supportProfileImage, userImage = userProfileImage)
            }
        }


    }


}