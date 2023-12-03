package com.arya.danesh.myresume

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.compose.customToolbar.CustomToolBar
import com.arya.danesh.myresume.compose.navigation.NavigationBar
import com.arya.danesh.myresume.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.theme.MyResumeTheme


class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentPage = remember { mutableStateOf("home") }
            val toolBarState = remember { mutableStateOf(ToolBarAnimationState.EXPENDED) }
            val isAnimationToolBarFinished = remember { mutableStateOf(true) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination


            MyResumeTheme(darkTheme = false,dynamicColor = false) {
                // A surface container using the 'background' color from the theme


                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.secondary
                                )
                            )
                        ),
                    backgroundColor = Color.Transparent,
                    scaffoldState = rememberScaffoldState(),
                    topBar = {
                        CustomToolBar(currentPage.value,
                            isAnimationRunningListener = {isRunning ->
                                isAnimationToolBarFinished.value = !isRunning
                                Log.d("tester ",isRunning.toString())
                            },
                            toolBarState = toolBarState.value
                        ) {

                        }
                    },
                    bottomBar = { NavigationBar(currentDestination){ mainItemNavigation->
                        if (isAnimationToolBarFinished.value)
                            if (currentDestination?.hierarchy?.any { it.route == mainItemNavigation.route } == false) {
                                navController.navigate(mainItemNavigation.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    currentPage.value=mainItemNavigation.route

                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = false

                                    }

                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true

//                                if (isExpended.value)
//                                    isAnimationToolBarFinished.value = false
//                                isExpended.value = false
                                }
                            }
                    } },
                    content = {
                        NavHost(navController = navController, startDestination = "Main") {
                            homeGraph(){isScrollInProgress , canScrollBackward ->
                                if (isScrollInProgress) {
                                    if (canScrollBackward) {
                                        toolBarState.value = ToolBarAnimationState.COLLAPSE
                                    }
                                    else
                                    {
                                        toolBarState.value = ToolBarAnimationState.EXPENDED
                                    }

                                }

                            }

                        }
                    }
                )


            }

        }

    }
}