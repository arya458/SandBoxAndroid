package com.arya.danesh.myresume.ui.pages.main

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.controller.graph.mainGraph
import com.arya.danesh.myresume.controller.route.MainNavigation
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.data.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.compose.customToolbar.CustomToolBar
import com.arya.danesh.myresume.ui.core.compose.navigation.NavigationBar
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.pages.main.compose.Background
import com.arya.danesh.myresume.ui.theme.elv_1


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main(navigateTo: (RootNavigation) -> Unit) {


    val sharedData: SharedViewModel = viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }
    Background()

    Log.d("Wtf", navController.currentDestination?.route + "")
    Scaffold(
            modifier =
            Modifier
                    .fillMaxSize()
                    .background(
                            color = Color.Transparent
                    ),
            backgroundColor = Color.Transparent,
            scaffoldState = scaffoldState,
            topBar = {
                CustomToolBar(
                        Modifier,
                )
            },

            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                NavigationBar(
                        Modifier,
                        currentDestination
                ) { mainItemNavigation ->
                    Log.d("Wtf", currentDestination?.route + "")

                    if (currentDestination?.hierarchy?.any { it == mainItemNavigation } == false) {
                        if (mainItemNavigation == MainNavigation.Main.Messenger)
                            navigateTo(RootNavigation.Root.Messenger)
                        else {
                            navController.navigate(mainItemNavigation.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                sharedData.currentPage.value = mainItemNavigation.route
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
            }
    ) {
        Surface(
                Modifier
                        .fillMaxSize()
                        .alpha(1f),
                color = MaterialTheme.colorScheme.background,
                elevation = elv_1,
                shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
        ) {

//            setToolbarState:(ToolBarAnimationState)->Unit
//            Child({toolBarState.value=it})
            NavHost(
                    modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    navController = navController,
                    startDestination = "Main"
            ) {
                mainGraph(navigateTo) { isScrollInProgress, canScrollBackward ->
                    if (isScrollInProgress) {
                        if (canScrollBackward) {
                            sharedData.toolBarState.value = ToolBarAnimationState.COLLAPSE
                        } else {
                            sharedData.toolBarState.value = ToolBarAnimationState.EXPENDED
                        }

                    }
                }


            }
        }


    }
}
