package com.sandbox.sandboxMessenger.ui.pages.loading

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.AnimLogo
import com.arya.danesh.coreui.texts.TextSubTittle
import com.arya.danesh.coreui.theme.elv_3
import com.sandbox.sandboxMessenger.di.viewModels.MessengerViewModel
import com.sandbox.sandboxmessenger.R
import kotlinx.coroutines.delay


@Composable
fun LoadingPage(
        navigateTo: (RootNavigation) -> Unit,
        messengerViewModel: MessengerViewModel = hiltViewModel()) {


    val isLoggedIn by messengerViewModel.isLoggedIn.collectAsState()


//    messengerViewModel.startService()
//    val coroutineScope = rememberCoroutineScope()


//    val loadingState = remember { mutableStateOf(state) }
//    val transition = updateTransition(sharedData.getAppState(), label = "Color State")
//    val color by transition.animateColor(
//            transitionSpec = {
//                tween(
//                        durationMillis = 800,
//                        delayMillis = 0,
//                        easing = FastOutSlowInEasing
//                )
//            }, label = "color"
//    )
//    { state ->
//
//        when (state) {
//            AppState.Normal -> MaterialTheme.colorScheme.primary
//            AppState.Update -> MaterialTheme.colorScheme.secondary
//            AppState.Error -> MaterialTheme.colorScheme.error
//        }
//    }
//
//    val errorAlpha by transition.animateFloat(
//            transitionSpec = {
//                tween(
//                        durationMillis = 800,
//                        delayMillis = 0,
//                        easing = FastOutSlowInEasing
//                )
//            }, label = "color"
//    )
//    { state ->
//
//        when (state) {
//
//            AppState.Normal -> 0f
//            AppState.Update -> 1f
//            AppState.Error -> 1f
//        }
//    }
//    val loadingAlpha by transition.animateFloat(
//            transitionSpec = {
//                tween(
//                        durationMillis = 800,
//                        delayMillis = 0,
//                        easing = FastOutSlowInEasing
//                )
//            }, label = "color"
//    )
//    { state ->
//
//        when (state) {
//            AppState.Normal -> 1f
//            AppState.Update -> 0f
//            AppState.Error -> 0f
//        }
//    }


//    val dynamicProperty = rememberLottieDynamicProperty(
//            property = LottieProperty.COLOR,
//            value = color.toArgb(),
//            keyPath = arrayOf(
//                    "**"
//            )
//    )

    if (isLoggedIn != null) {
        if (isLoggedIn!!)
            navigateTo(RootNavigation.Root.MainPage)
        else
            navigateTo(RootNavigation.Root.Login)
    }
    LaunchedEffect(key1 = true) {
        delay(1000)
        messengerViewModel.stat()
    }





    Surface(
            Modifier
                    .safeDrawingPadding()
                    .fillMaxSize(1f)
                    .animateContentSize(),
            color = MaterialTheme.colorScheme.background
    ) {
//        LiveBG()


        AnimLogo(300.dp,
                color = MaterialTheme.colorScheme.primary,
                animSpeed = 0.2f,
                shadowElevation = elv_3,
                logoID = R.drawable.icon,
                lottieAnimationID = R.raw.new_loading_anim)
        Column(Modifier
                .fillMaxSize(), Arrangement.Bottom)
        {

            Box(
                    Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(), contentAlignment = Alignment.Center) {


                Column(
                        Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(bottom = 40.dp), Arrangement.Bottom) {
                    TextSubTittle(
                            text = "Loading ...",
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .scale(1f)
                                    .alpha(1f)
                                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                                    .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary,
                    )

//                    LinearProgressIndicator(
//                            progress = 0.5f,
//                            Modifier
//                                    .fillMaxWidth()
//                                    .height(20.dp)
//                                    .scale(1f)
//                                    .alpha(1f)
//                                    .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
//                                    .shadow(3.dp, RoundedCornerShape(15.dp), clip = true),
//                            color = MaterialTheme.colorScheme.primary,
//                            strokeCap = StrokeCap.Round,
////                    backgroundColor = progressBarEmpty.copy(0.5f)
//
//                    )
                }
//                Column(Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()
//                        .padding(bottom = 5.dp), Arrangement.Bottom) {
//
//                    Text(
//                            text = "Error : Failed To Download Data",
//                            modifier = Modifier
//                                    .fillMaxWidth()
//                                    .scale(errorAlpha)
//                                    .alpha(errorAlpha)
//                                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
//                                    .wrapContentHeight(),
//                            textAlign = TextAlign.Center,
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = MaterialTheme.colorScheme.error,
//                    )
//                    Button(modifier = Modifier
//                            .wrapContentSize()
//                            .scale(errorAlpha)
//                            .alpha(errorAlpha)
//                            .align(Alignment.CenterHorizontally),
//                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.errorContainer),
//                            colors = ButtonDefaults.buttonColors(
//                                    backgroundColor = Color.Transparent,
//                                    contentColor = MaterialTheme.colorScheme.error,
//                                    disabledBackgroundColor = Color.Transparent,
//                                    disabledContentColor = Color.Transparent,
//                            ),
//
//                            onClick = { sharedData.setAppState(AppState.Normal) }) {
//                        Text(
//                                text = "Try Again",
//                                modifier = Modifier
//                                        .wrapContentSize()
//                                        .scale(errorAlpha)
//                                        .wrapContentHeight(),
//                                textAlign = TextAlign.Center,
//                                style = MaterialTheme.typography.bodyMedium,
//                                color = MaterialTheme.colorScheme.errorContainer,
//                        )
//                    }
//                }
            }
        }

    }

//    if (sharedData.appState.value == AppState.Normal)
//        LaunchedEffect(key1 = true) {
//            delay(5000)
//            sharedData.appState.value=AppState.Update
//        }
//    if (sharedData.appState.value == AppState.Update)
//        LaunchedEffect(key1 = true) {
//            delay(5000)
//            sharedData.appState.value=AppState.Error
//        }

//    navigateTo(SplashNavigation.Main)

}