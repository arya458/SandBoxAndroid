package com.sandbox.sandboxMessenger.ui.pages.messenger

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sandbox.sandboxMessenger.di.viewModels.ApiViewModel
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.Texts.TextTittle
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessengerBottomBar
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessengerTittleBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import net.folivo.trixnity.clientserverapi.client.start

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessengerPage(
        navigateTo: (RootNavigation) -> Unit,
        apiViewModel: ApiViewModel = hiltViewModel()
) {


    val message = rememberSaveable { mutableStateOf("") }
    val profile by apiViewModel.supportProfile.collectAsState()
    val profileImage by apiViewModel.supportProfileImage.collectAsState()
    val messageList by apiViewModel.messagesList.collectAsState()
    val lazyState = rememberLazyListState()
    val nextBatch: MutableStateFlow<String> = MutableStateFlow("")


    apiViewModel.makeChatRoom()

//    apiViewModel.startListeningForMessages()

    LaunchedEffect(Unit) {
        MainScope().launch(Unconfined) {
            println("Started")


            apiViewModel.matrix?.sync?.sync(since = null)?.onSuccess { res ->

                nextBatch.value = res.nextBatch
            }

            println(nextBatch.value)

            apiViewModel.matrix?.sync?.start(
                    setBatchToken = { nextBatch.value = it },
                    scope = CoroutineScope(this.coroutineContext),
                    wait = false,
                    getBatchToken = { if (nextBatch.value == "") null else nextBatch.value },
            )
//            delay(1.minutes) // wait some time
//            apiViewModel.matrix?.sync?.stop()
            Log.d("testMatrix", "Service: Ended")
        }
    }

//    DisposableEffect(key1 = , effect = )
//    Log.d("testMatrix", "ArrayList: "+messageList.value.size)

    Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MessengerTittleBar(username = profile.displayName.toString(),
                        imageBitmap = profileImage?.asImageBitmap(),
                        isOnline = false,
                        onBackClick = { navigateTo(RootNavigation.Root.MainPage) },
                        userOnClick = { navigateTo(RootNavigation.Root.ProfilePage) })
            },
            floatingActionButton = {

            },
            bottomBar = {
                MessengerBottomBar(
                        userMessage = message.value,
                        setUserMessage = { text: String -> message.value = text }
                ) {
                    apiViewModel.sendMessage(message.value)
                    message.value=""

                }
            }

    ) {

        LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
                state = lazyState,
        ) {
            itemsIndexed(messageList) { _, item ->
                TextTittle(text = item.body, modifier = Modifier.padding(10.dp), color = MaterialTheme.colorScheme.onBackground)
            }
        }


    }


}