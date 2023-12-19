package com.arya.danesh.myresume.pages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.arya.danesh.myresume.homeGraph
import com.arya.danesh.myresume.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.theme.MyResumeTheme
import com.arya.danesh.myresume.ui.theme.elv_1

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main() {

    val navController = rememberNavController()
    val currentPage = remember { mutableStateOf("home") }
    val toolBarState = remember { mutableStateOf(ToolBarAnimationState.EXPENDED) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
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
                shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
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