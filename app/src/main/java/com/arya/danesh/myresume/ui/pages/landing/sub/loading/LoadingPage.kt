package com.arya.danesh.myresume.ui.pages.landing.sub.loading

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.data.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.compose.AnimLogo
import com.arya.danesh.myresume.ui.core.state.AppState

@Composable
fun LoadingPage(navigateTo: (RootNavigation) -> Unit) {


    val sharedData: SharedViewModel = viewModel()

//    val loadingState = remember { mutableStateOf(state) }
    val transition = updateTransition(sharedData.appState.value, label = "Color State")
    val color by transition.animateColor(
            transitionSpec = {
                tween(
                        durationMillis = 800,
                        delayMillis = 0,
                        easing = FastOutSlowInEasing
                )
            }, label = "color"
    )
    { state ->

        when (state) {
            AppState.Normal -> MaterialTheme.colorScheme.primary
            AppState.Update -> MaterialTheme.colorScheme.secondary
            AppState.Error -> MaterialTheme.colorScheme.error
        }
    }

    val errorAlpha by transition.animateFloat(
            transitionSpec = {
                tween(
                        durationMillis = 800,
                        delayMillis = 0,
                        easing = FastOutSlowInEasing
                )
            }, label = "color"
    )
    { state ->

        when (state) {

            AppState.Normal -> 0f
            AppState.Update -> 1f
            AppState.Error -> 1f
        }
    }
    val loadingAlpha by transition.animateFloat(
            transitionSpec = {
                tween(
                        durationMillis = 800,
                        delayMillis = 0,
                        easing = FastOutSlowInEasing
                )
            }, label = "color"
    )
    { state ->

        when (state) {
            AppState.Normal -> 1f
            AppState.Update -> 0f
            AppState.Error -> 0f
        }
    }


//    val dynamicProperty = rememberLottieDynamicProperty(
//            property = LottieProperty.COLOR,
//            value = color.toArgb(),
//            keyPath = arrayOf(
//                    "**"
//            )
//    )




    Surface(
            Modifier
                    .fillMaxSize(1f)
                    .clickable { navigateTo(RootNavigation.Root.Login) }
                    .animateContentSize(),
            color = MaterialTheme.colorScheme.background
    ) {
//        LiveBG()


        AnimLogo(300.dp,color, animSpeed =0.2f)
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
                    Text(
                            text = "Loading",
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .scale(loadingAlpha)
                                    .alpha(loadingAlpha)
                                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                                    .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                    )
                    LinearProgressIndicator(
                            progress = 0.5f,
                            Modifier
                                    .fillMaxWidth()
                                    .height(20.dp)
                                    .scale(loadingAlpha)
                                    .alpha(loadingAlpha)
                                    .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                                    .shadow(3.dp, RoundedCornerShape(15.dp), clip = true),
                            color = MaterialTheme.colorScheme.primary,
                            strokeCap = StrokeCap.Round,
//                    backgroundColor = progressBarEmpty.copy(0.5f)

                    )
                }
                Column(Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 40.dp), Arrangement.Bottom) {

                    Text(
                            text = "Error : Failed To Download Data",
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .scale(errorAlpha)
                                    .alpha(errorAlpha)
                                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                                    .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                    )
                    Button(modifier = Modifier
                            .wrapContentSize()
                            .scale(errorAlpha)
                            .alpha(errorAlpha)
                            .align(Alignment.CenterHorizontally),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.errorContainer),
                            colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = MaterialTheme.colorScheme.error,
                                    disabledBackgroundColor = Color.Transparent,
                                    disabledContentColor = Color.Transparent,
                            ),

                            onClick = { sharedData.appState.value=AppState.Normal }) {
                        Text(
                                text = "Try Again",
                                modifier = Modifier
                                        .wrapContentSize()
                                        .scale(errorAlpha)
                                        .wrapContentHeight(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.errorContainer,
                        )
                    }
                }
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