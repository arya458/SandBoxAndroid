package com.arya.danesh.myresume.ui.pages.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.ui.controller.graph.mainGraph
import com.arya.danesh.myresume.ui.controller.route.MainNavigation
import com.arya.danesh.myresume.ui.controller.route.RootNavigation
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.compose.customToolbar.CustomToolBar
import com.arya.danesh.myresume.ui.core.compose.navigation.NavigationBar
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.pages.main.compose.Background
import com.arya.danesh.myresume.ui.pages.main.compose.SideMenu
import com.arya.danesh.myresume.ui.theme.elv_1


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main(navigateTo: (RootNavigation) -> Unit, sharedData: SharedViewModel = hiltViewModel()) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }


    val stiffness = 260f

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp


    val transition = updateTransition(sharedData.getmenuState(), label = "ToolBar State")


    val mainRotation by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            MenuState.EXPENDED -> 10f
            MenuState.COLLAPSE -> 0f
        }

    }
    val mainScale by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            MenuState.EXPENDED -> 0.8f
            MenuState.COLLAPSE -> 1f
        }

    }
    val mainTranslation by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            MenuState.EXPENDED -> (screenWidth / 3) * 2
            MenuState.COLLAPSE -> 0.dp
        }

    }





    Surface(Modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount > 0) {
                        sharedData.setmenuState(MenuState.EXPENDED)
                    } else {
                        sharedData.setmenuState(MenuState.COLLAPSE)
                    }

                }
            }

            .fillMaxSize(), color = Color.Transparent) {


        Background()


        SideMenu(navigateTo, stiffness = stiffness)

        Log.d("Wtf", navController.currentDestination?.route + "")




        Scaffold(
                modifier =
                Modifier
                        .fillMaxSize()
                        .graphicsLayer {

                            scaleY = mainScale
                            scaleX = mainScale
                            rotationY = mainRotation
                            translationX = mainTranslation.toPx()

                        }
                        .background(
                                color = Color.Transparent
                        ),
                backgroundColor = Color.Transparent,
                scaffoldState = scaffoldState,
                topBar = {
                    CustomToolBar(stiffness = stiffness) {
                        if (sharedData.getmenuState() == MenuState.EXPENDED)
                            sharedData.setmenuState(MenuState.COLLAPSE)
                        else
                            sharedData.setmenuState(MenuState.EXPENDED)
                    }
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
                                    sharedData.setCurrentPage(mainItemNavigation.route)
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
                            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
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
                                sharedData.setToolBarState(ToolBarAnimationState.COLLAPSE)
                            } else {
                                sharedData.setToolBarState(ToolBarAnimationState.EXPENDED)
                            }

                        }
                    }


                }
            }


        }


    }


}
