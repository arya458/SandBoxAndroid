package com.arya.danesh.myresume.ui.pages.rootPages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.controller.SplashNavigation
import com.arya.danesh.myresume.ui.core.compose.customToolbar.CustomToolBar
import com.arya.danesh.myresume.ui.core.compose.navigation.NavigationBar
import com.arya.danesh.myresume.ui.controller.homeGraph
import com.arya.danesh.myresume.ui.core.state.AppState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.theme.elv_1
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main(navigateTo: (SplashNavigation) -> Unit) {

    val navController = rememberNavController()
    val currentPage = remember { mutableStateOf("home") }
    val toolBarState = remember { mutableStateOf(ToolBarAnimationState.EXPENDED) }
    val appState = remember { mutableStateOf(AppState.Normal) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()


    val transition = updateTransition(toolBarState.value, label = "ToolBar State")
    val appTransition = updateTransition(appState.value, label = "App State")


    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

//    val OldBgColor = MaterialTheme.colorScheme.background
//    val bgColor1 = remember { Animatable(OldBgColor) }
//    val bgColor2 = remember { Animatable(OldBgColor) }

//    LaunchedEffect(Unit) {
//
//        bgColor1.animateTo(bgColorNext1, animationSpec = tween(500,500))
//        bgColor2.animateTo(bgColorNext2, animationSpec = tween(500))
//    }

    val blurAnim by transition.animateDp(
        transitionSpec = {
            spring(
                stiffness = 200f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 100.dp
            ToolBarAnimationState.COLLAPSE -> 150.dp
        }


    }


    val speedAnim by appTransition.animateFloat(
        transitionSpec = {
            spring(
                stiffness = 200f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"


    ) { state ->

        when (state) {
            AppState.Normal -> 0.5f
            AppState.Update -> 1f
            AppState.Error -> 2f
        }


    }

    val colorAnim by appTransition.animateColor(
        transitionSpec = {
            spring(
                stiffness = 200f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"


    ) { state ->

        when (state) {
            AppState.Normal -> MaterialTheme.colorScheme.primary
            AppState.Update -> MaterialTheme.colorScheme.secondary
            AppState.Error -> MaterialTheme.colorScheme.error
        }


    }


    val composition3 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bg))
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = colorAnim.toArgb(),
            keyPath = arrayOf(
                "**"
            )
        )
    )








    Surface(
        Modifier
            .fillMaxSize()
            .clickable {

                // todo: Just for testing

                when (appState.value) {
                    AppState.Update -> appState.value = AppState.Error
                    AppState.Normal -> appState.value = AppState.Update
                    AppState.Error -> appState.value = AppState.Normal
                }
                scope.launch {
                    when (appState.value) {
                        AppState.Error ->

                            snackbarHostState.showSnackbar(
                                message = "Error: Please Turn Your Wifi On"
                            )


                        AppState.Update ->
                            snackbarHostState.showSnackbar(
                                message = "Your Using an Old Version For Data Please Update"
                            )


                        else ->
                            snackbarHostState.showSnackbar(
                                message = "Welcome to my app"
                            )

                    }
                }

            }) {
        if (android.os.Build.VERSION.SDK_INT > 31) {
            LottieAnimation(
                composition = composition3,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .blur(blurAnim, BlurredEdgeTreatment.Unbounded),
                iterations = LottieConstants.IterateForever,
                dynamicProperties = dynamicProperties,
                contentScale = ContentScale.FillBounds,
                reverseOnRepeat = true,
                speed = speedAnim

            )

        }
    }

    Scaffold(
        modifier =

        if (android.os.Build.VERSION.SDK_INT <= 31) {
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )

                    )
                )
        } else {
            Modifier
                .fillMaxSize()
                .background(
                    color = Color.Transparent
                )
        },
        backgroundColor = Color.Transparent,
        scaffoldState = scaffoldState,
        topBar = {
            CustomToolBar(
                Modifier,
                currentPage.value,
                isAnimationRunningListener = { isRunning ->
                    Log.d("tester ", isRunning.toString())
                },
                toolBarState = toolBarState.value
            ) {

            }
        },

        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar(
                Modifier,
                currentDestination
            ) { mainItemNavigation ->

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
        Surface(
            Modifier
                .fillMaxSize()
                .alpha(1f),
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
                homeGraph(navigateTo) { isScrollInProgress, canScrollBackward ->
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
