package com.arya.danesh.myresume.ui.pages.landing

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.core.state.AppState

@Composable
fun Intro(navController: NavHostController, navigateTo: (RootNavigation) -> Unit, appState: AppState, setAppState :(AppState) -> Unit) {
    NavHost(
            modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            navController = navController,
            startDestination = "Intro"
    ) {
//        introGraph(navigateTo,appState,setAppState)
    }
}