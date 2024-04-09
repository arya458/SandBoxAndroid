package com.arya.danesh.myresume

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.theme.MyResumeTheme
import com.arya.danesh.myresume.di.viewModels.AppsViewModel
import com.arya.danesh.myresume.ui.controller.graph.rootGraph
import com.arya.danesh.utilities.CoreUtility.computeWindowSize
import com.sandbox.sandboxMessenger.di.viewModels.MessengerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val messengerViewModel: MessengerViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        computeWindowSize(this)
        setContent {
            val navController = rememberNavController()
            MyResumeTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                NavHost(
                        modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(MaterialTheme.colorScheme.background),
                        navController = navController,
                        startDestination = "Root"
                ) {

                    rootGraph(navigateTo = { dis ->
                        //todo: UnComment Later We Need PopBackStack (For know we need to Debug)
                        when(dis.route){

                            RootNavigation.Root.MainPage.route -> {
                                navController.popBackStack()
                            }
                            RootNavigation.Root.Login.route -> {
                                navController.popBackStack()
                            }
                            RootNavigation.Root.Register.route -> {
//                                navController.popBackStack()
                            }
                            RootNavigation.Root.ReadBlog.route -> {
//                                navController.popBackStack()
                            }
                            RootNavigation.Root.Messenger.route -> {
//                                navController.popBackStack()
                            }
                            RootNavigation.Root.ProfilePage.route -> {
//                                navController.popBackStack()
                            }


                        }
                        navController.navigate(dis.route)


                    })

                }
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        messengerViewModel.stopService()
    }



}