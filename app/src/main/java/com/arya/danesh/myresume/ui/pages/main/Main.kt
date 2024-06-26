package com.arya.danesh.myresume.ui.pages.main

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.Background
import com.arya.danesh.coreui.theme.elv_1
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.controller.graph.mainGraph
import com.arya.danesh.myresume.ui.controller.route.MainNavigation
import com.arya.danesh.myresume.ui.core.component.customToolbar.CustomToolBar
import com.arya.danesh.myresume.ui.core.component.navigation.NavigationBar
import com.arya.danesh.myresume.ui.pages.main.component.SideMenu
import com.arya.danesh.myresume.ui.pages.main.component.SubPageTittle
import com.arya.danesh.utilities.state.MenuState
import com.arya.danesh.utilities.state.ToolBarAnimationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main(navigateTo: (RootNavigation) -> Unit, sharedData: SharedViewModel = hiltViewModel()) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }
    val isExitTime = remember { mutableStateOf(false) }
    val isDoneDragging = remember {
        mutableStateOf(true)
    }
    val stiffness = 260f
    val drawer: Float = 150f

    val coroutineScope = rememberCoroutineScope()


    BackHandler {
        if (sharedData.getMenuState() == MenuState.EXPENDED)
            sharedData.setMenuState(MenuState.COLLAPSE)
        else
            if (isExitTime.value)
                (sharedData.getAppContext() as Activity).finish()
            else {
                isExitTime.value = true
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = "For Exit Press Back Again", duration = SnackbarDuration.Short)
                }
                coroutineScope.launch {
                    delay(4000L)
                    isExitTime.value = false
                }
            }
    }


//    val configuration = LocalConfiguration.current


//    val screenWidth = configuration.screenWidthDp.dp.value
//    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
//    val width = metrics.bounds.width()
//    val screenWidth = screenWidth

    val tranX = remember {
        Animatable(
                0f)
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

    }


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
    SideEffect {
        if (isDoneDragging.value)
            if (sharedData.getMenuState() == MenuState.EXPENDED)
                coroutineScope.launch {
                    tranX.animateTo(10f * drawer)
                }
            else
                coroutineScope.launch {
                    tranX.animateTo(0f)
                }
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
                        isDoneDragging.value = true
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

                            sharedData.setMenuState(
                                    if (targetX == drawer)
                                        MenuState.EXPENDED
                                    else
                                        MenuState.COLLAPSE
                            )

                        }
                    },
                    onDragStarted = {
                        isDoneDragging.value = false
                    }
            )


            .fillMaxSize(), color = Color.Transparent) {


        Background(resId = R.raw.bg)


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
            SideMenu(navigateTo)
        }

//        DisposableEffect(){
//
//        }


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
                        .shadow(mainShadow, shape = RoundedCornerShape(lerp(0, 15, tranX.value / drawer).dp), clip = true)
                        .clickable(enabled = sharedData.getMenuState() == MenuState.EXPENDED) {
                            sharedData.setMenuState(MenuState.COLLAPSE)
                        },
                backgroundColor = Color.Transparent,
                scaffoldState = scaffoldState,
                topBar = {
                    CustomToolBar(stiffness = stiffness) {
                        coroutineScope.launch {
                            tranX.animateTo(10f * drawer)
                        }
                        sharedData.setMenuState(MenuState.EXPENDED)
                    }
                },

                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = {
                    NavigationBar(
                            Modifier,
                            currentDestination
                    ) { mainItemNavigation ->
                        Log.d("Wtf", currentDestination?.route + "")
//                        if (sharedData.getmenuState() == MenuState.COLLAPSE)
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
                Column(Modifier.fillMaxSize(), Arrangement.Top, Alignment.CenterHorizontally) {
                    SubPageTittle(sharedData.getCurrentPage(), sharedData.getToolBarState())
                    NavHost(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                            navController = navController,
                            startDestination = "Main"
                    ) {

                        //todo:fix toolbar Collapse Lagggggg !!!!
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


}


