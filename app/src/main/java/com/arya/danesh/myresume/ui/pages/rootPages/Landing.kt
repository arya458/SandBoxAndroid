package com.arya.danesh.myresume.ui.pages.rootPages

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.arya.danesh.myresume.ui.controller.intoGraph

@Composable
fun Landing(navController: NavHostController) {
    NavHost(
            modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            navController = navController,
            startDestination = "Into"
    ) {
        intoGraph() {


            //todo: UnComment Later We Need PopBackStack (For know we need to Debug)
//                        navController.popBackStack()
            navController.navigate(it.route)
        }
    }
}