package com.arya.danesh.myresume.ui.pages.messenger

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.arya.danesh.myresume.ui.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.pages.messenger.compose.MessengerBottomBar
import com.arya.danesh.myresume.ui.pages.messenger.compose.MessengerTittleBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessengerPage(
        navigateTo: (RootNavigation) -> Unit,
) {


    val message = rememberSaveable { mutableStateOf("") }

    Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MessengerTittleBar(username = "Aria Danesh" ,
                        isOnline = false,
                        onBackClick = {navigateTo(RootNavigation.Root.MainPage)},
                        userOnClick = {navigateTo(RootNavigation.Root.ProfilePage)}) },
            floatingActionButton = {

            },
            bottomBar = { MessengerBottomBar(userMessage = message.value, setUserMessage = {text: String -> message.value=text })}


    ) {



    }



}