package com.arya.danesh.myresume.ui.pages.main

import android.annotation.SuppressLint
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.view.ViewCompat.getDisplay
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.controller.graph.mainGraph
import com.arya.danesh.myresume.ui.controller.route.MainNavigation
import com.arya.danesh.myresume.ui.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.core.component.customToolbar.CustomToolBar
import com.arya.danesh.myresume.ui.core.component.navigation.NavigationBar
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.pages.main.component.Background
import com.arya.danesh.myresume.ui.pages.main.component.SideMenu
import com.arya.danesh.myresume.ui.theme.elv_1
import com.arya.danesh.myresume.ui.theme.elv_3
import com.arya.danesh.utilities.CoreUtility.dpToPx
import com.arya.danesh.utilities.CoreUtility.screenWidth
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main(navigateTo: (RootNavigation) -> Unit, sharedData: SharedViewModel = hiltViewModel()) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }


    val stiffness = 260f


    val coroutineScope = rememberCoroutineScope()
    val drawer = 150f

//    val configuration = LocalConfiguration.current


//    val screenWidth = configuration.screenWidthDp.dp.value
//    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
//    val width = metrics.bounds.width()
//    val screenWidth = screenWidth

    val tranX = remember {
        Animatable(
                when (sharedData.getmenuState()) {
                    MenuState.COLLAPSE -> {

                        0f

                    }

                    MenuState.EXPENDED -> {
                        10f
                    }
                })
    }

    val transition = updateTransition(tranX.value, label = "ToolBar State")


    val mainRotation by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { x ->

        lerp(0f, 10f, x / drawer)
//        when (state) {
//            MenuState.EXPENDED -> 10f
//            MenuState.COLLAPSE -> 0f
//        }

    }

    val mainScale by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { x ->

        lerp(1f, 0.5f, x / drawer)
//        when (state) {
//            MenuState.EXPENDED -> 0.8f
//            MenuState.COLLAPSE -> 1f
//        }

    }
    val mainTranslation by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { x ->

        lerp(0, 120, x / drawer).dp

//        when (state) {
//            MenuState.EXPENDED -> (screenWidth / 3) * 2
//            MenuState.COLLAPSE -> 0.dp
//        }

    }

    val mainShadow by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { x ->

        lerp(0, 1, x / drawer).dp

//        when (state) {
//            MenuState.EXPENDED -> (screenWidth / 3) * 2
//            MenuState.COLLAPSE -> 0.dp
//        }

    }
//    val mainCorner by transition.animateDp(
//            transitionSpec = {
//                spring(
//                        stiffness = stiffness,
//                        dampingRatio = 0.46f,
//                )
//            }, label = "color"
//
//    ) { x ->
//
//        lerp(1, 15, x / drawer).dp
//
////        when (state) {
////            MenuState.EXPENDED -> (screenWidth / 3) * 2
////            MenuState.COLLAPSE -> 0.dp
////        }
//
//    }

    val menuScale by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { x ->

        lerp(0f, 1f, x / drawer)

//        when (state) {
//            MenuState.EXPENDED -> 1f
//            MenuState.COLLAPSE -> 0f
//        }

    }
    val menuTranslation by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { x ->

        lerp((200 * -1), 0, x / drawer).dp


//        when (state) {
//            MenuState.EXPENDED -> 0.dp
//            MenuState.COLLAPSE -> (200.dp * -1)
//        }

    }


    tranX.updateBounds(0f, drawer)

    val draggableState = rememberDraggableState(onDelta = {
        coroutineScope.launch {
            tranX.snapTo(tranX.value + it)
        }

    })
    val decay = rememberSplineBasedDecay<Float>()

    Surface(Modifier
            .draggable(draggableState, orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        val decayX = decay.calculateTargetValue(tranX.value, velocity)

                        coroutineScope.launch {
                            val targetX = if (decayX > drawer * 0.5) {
                                drawer
                            } else {
                                0f
                            }
                            val canReachTarget = (decayX > targetX && targetX == drawer) || (decayX < targetX && targetX == 0f)

                            if (canReachTarget)
                                tranX.animateDecay(velocity, decay)
                            else
                                tranX.animateTo(targetX, initialVelocity = velocity)
                            sharedData.setmenuState(
                                    if (targetX == drawer)
                                        MenuState.EXPENDED
                                    else
                                        MenuState.COLLAPSE
                            )


                        }
                    },
                    onDragStarted = {
                        sharedData.setmenuState(MenuState.COLLAPSE)
                    }
            )
//            .pointerInput(Unit) {
//                detectHorizontalDragGestures { change, drag ->
//                    dragAmount.value = drag
//
//
////                    if (drag > 0) {
////                        Log.d("menuChangeDrag", "change: " + change.scrollDelta.toString())
////                        sharedData.setmenuState(MenuState.EXPENDED)
////                    } else {
////                        Log.d("menuChangeDrag", "change: " + change.scrollDelta.toString())
////                        sharedData.setmenuState(MenuState.COLLAPSE)
////                    }
//
//                }
//            }

            .fillMaxSize(), color = Color.Transparent) {


        Background()


        //Menu
        Row(
                Modifier
                        .graphicsLayer {
                            alpha = menuScale
                            translationX = menuTranslation.toPx()
                        }
                        .fillMaxSize(),
                Arrangement.Start,
        ) {
            SideMenu(navigateTo,
                    stiffness = stiffness)
        }





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
                        )
                        .shadow(mainShadow, shape = RoundedCornerShape(lerp(0, 15, tranX.value / drawer).dp), clip = true),
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

