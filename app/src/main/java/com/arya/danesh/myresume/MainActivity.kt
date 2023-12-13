package com.arya.danesh.myresume

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.compose.customToolbar.CustomToolBar
import com.arya.danesh.myresume.compose.navigation.NavigationBar
import com.arya.danesh.myresume.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.theme.MyResumeTheme
import com.arya.danesh.myresume.ui.theme.elv_1


class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentPage = remember { mutableStateOf("home") }
            val toolBarState = remember { mutableStateOf(ToolBarAnimationState.EXPENDED) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val scaffoldState = rememberScaffoldState()

            MyResumeTheme(darkTheme = false, dynamicColor = false) {
                // A surface container using the 'background' color from the theme

//
//                Surface(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(
//                                    MaterialTheme.colorScheme.primary,
//                                    MaterialTheme.colorScheme.secondary
//                                )
//                            )
//                        ),
//                    color = Color.Transparent,
//                ) {
//                    Column(
//                        Modifier
//                            .fillMaxSize()
//                            .animateContentSize()
//                        ,Arrangement.Top) {
//                        CustomToolBar(
//                            currentPage.value,
//                            isAnimationRunningListener = { isRunning ->
//                                Log.d("tester ", isRunning.toString())
//                            },
//                            toolBarState = toolBarState.value
//                        ) {
//
//                        }
//
//                        Surface(Modifier
//                            .fillMaxSize(),
//                            color = MaterialTheme.colorScheme.background,
//                            elevation = elv_1,
//                            shape = RoundedCornerShape(15.dp,15.dp,0.dp,0.dp)
//                        ) {
//                            NavHost(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .fillMaxHeight(),
//                                navController = navController,
//                                startDestination = "Main"
//                            ) {
//                                homeGraph() { isScrollInProgress, canScrollBackward ->
//                                    if (isScrollInProgress) {
//                                        if (canScrollBackward) {
//                                            toolBarState.value = ToolBarAnimationState.COLLAPSE
//                                        } else {
//                                            toolBarState.value = ToolBarAnimationState.EXPENDED
//                                        }
//
//                                    }
//
//                                }
//
//                            }
//                        }
//
//
//                    }
//                    Column(Modifier.fillMaxSize(),Arrangement.Bottom) {
//                        NavigationBar(currentDestination) { mainItemNavigation ->
//
//                            if (currentDestination?.hierarchy?.any { it.route == mainItemNavigation.route } == false) {
//                                navController.navigate(mainItemNavigation.route) {
//                                    // Pop up to the start destination of the graph to
//                                    // avoid building up a large stack of destinations
//                                    // on the back stack as users select items
//                                    currentPage.value = mainItemNavigation.route
//                                    popUpTo(navController.graph.findStartDestination().id) {
//                                        saveState = false
//
//                                    }
//
//                                    // Avoid multiple copies of the same destination when
//                                    // reselecting the same item
//                                    launchSingleTop = true
//                                    // Restore state when reselecting a previously selected item
//                                    restoreState = true
//
////                                if (isExpended.value)
////                                    isAnimationToolBarFinished.value = false
////                                isExpended.value = false
//                                }
//                            }
//                        }
//                    }
//                }


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
                    scaffoldState = scaffoldState,
                    topBar = {
                        CustomToolBar(
                            currentPage.value,
                            isAnimationRunningListener = { isRunning ->
                                Log.d("tester ", isRunning.toString())
                            },
                            toolBarState = toolBarState.value
                        ) {

                        }
                    },
                    bottomBar = {
                        NavigationBar(currentDestination) { mainItemNavigation ->

                            if (currentDestination?.hierarchy?.any { it.route == mainItemNavigation.route } == false) {
                                navController.navigate(mainItemNavigation.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    currentPage.value = mainItemNavigation.route
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
                        }
                    }
                ) {
                    Surface(Modifier
                        .fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                        elevation = elv_1,
                        shape = RoundedCornerShape(15.dp,15.dp,0.dp,0.dp)
                    ) {
                        NavHost(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            homeGraph() { isScrollInProgress, canScrollBackward ->
                                if (isScrollInProgress) {
                                    if (canScrollBackward) {
                                        toolBarState.value = ToolBarAnimationState.COLLAPSE
                                    } else {
                                        toolBarState.value = ToolBarAnimationState.EXPENDED
                                    }

                                }

                            }

                        }
                    }
                }


            }

        }

    }
}