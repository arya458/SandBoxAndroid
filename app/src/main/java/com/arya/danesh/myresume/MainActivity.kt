package com.arya.danesh.myresume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.controller.graph.rootGraph
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.data.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.theme.MyResumeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        installSplashScreen().also {
//            it.setKeepOnScreenCondition(SplashScreen.KeepOnScreenCondition {
//                true
//            })
//        }
        setContent {

            val navController = rememberNavController()
//            val sharedData: SharedViewModel = viewModel()

            MyResumeTheme(darkTheme = false, dynamicColor = false) {

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
//                Intro(navController,{},appState.value,{appState.value=it})
            }
//                Main()

        }

    }

}