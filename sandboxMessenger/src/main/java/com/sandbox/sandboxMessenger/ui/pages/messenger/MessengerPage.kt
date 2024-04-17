package com.sandbox.sandboxMessenger.ui.pages.messenger

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.texts.TextSubTittle
import com.sandbox.sandboxMessenger.di.viewModels.MessengerViewModel
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessageView
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessengerBottomBar
import com.sandbox.sandboxMessenger.ui.pages.messenger.component.MessengerTittleBar
import com.sandbox.sandboxmessenger.R
import kotlinx.coroutines.launch

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
    val snackbarHostState = remember { SnackbarHostState() }



    messengerViewModel.makeChatRoom()

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
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                MessengerBottomBar(
                        userMessage = message.value,
                        setUserMessage = { text: String -> message.value = text }
                ) {
                    if (message.value.isNotEmpty()) {
                        messengerViewModel.sendMessage(message.value)
                        message.value = ""
                    }else{
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                    "You Can't Send An Empty Message Type Something First And TryAgain"
                            )
                        }
                    }
                }
            }

    ) {
        Surface(
                Modifier
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(15.dp)),
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(15.dp),
        ) {
            if (messageList.isEmpty())
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


                    Image(
                            painter = painterResource(R.drawable.no_message),
                            contentDescription = "",
                            Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth(0.5f),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground.copy(0.7f)),
                            contentScale = ContentScale.Inside

                    )
                    Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp))
                    TextSubTittle(modifier = Modifier.wrapContentSize(), text = "No Messages Yet ...", color = MaterialTheme.colorScheme.onBackground.copy(0.7f))

                }

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


}