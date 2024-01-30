package com.arya.danesh.myresume.ui.pages.subPages.splash

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.arya.danesh.myresume.ui.controller.SplashNavigation
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.core.state.AppState
import kotlinx.coroutines.delay

@Composable
fun LoadingPage(navigateTo: (SplashNavigation) -> Unit) {


    val loadingState = remember { mutableStateOf(AppState.Normal) }
    val transition = updateTransition(loadingState, label = "Color State")
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

        when (state.value) {
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

        when (state.value) {

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

        when (state.value) {
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

    val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.min_3))
    val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_4))
    val composition3 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.blob_anim_demo))

    val dynamicProperties = rememberLottieDynamicProperties(rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(),
            keyPath = arrayOf(
                    "**"
            )
    ))


    Surface(
        Modifier
            .fillMaxSize(1f)
            .animateContentSize(),
            color = MaterialTheme.colorScheme.background
    ) {
//        LiveBG()


        Box(
            Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center) {
            LottieAnimation(
                    composition = composition1,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.35f),
                    iterations = LottieConstants.IterateForever,
                    dynamicProperties = dynamicProperties,
                    reverseOnRepeat = true,
                    speed = if (loadingState.value == AppState.Normal) 0.2f else 0.6f

            )
            LottieAnimation(
                    composition = composition2,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.35f),
                    iterations = LottieConstants.IterateForever,
                    dynamicProperties = dynamicProperties,
                    reverseOnRepeat = true,
                    speed = if (loadingState.value == AppState.Normal) 0.4f else 0.8f

            )
            LottieAnimation(
                    composition = composition3,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.35f),
                    iterations = LottieConstants.IterateForever,
                    dynamicProperties = dynamicProperties,
                    reverseOnRepeat = true,
                    speed = if (loadingState.value == AppState.Normal) 0.6f else 1f

            )
            Image(
                    painter = painterResource(R.drawable.kotlin),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .shadow(5.dp, CircleShape, clip = true)
                        .clickable {
                            navigateTo(SplashNavigation.Main)
                        }

            )
        }
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

                            onClick = { loadingState.value = AppState.Normal }) {
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

    if (loadingState.value == AppState.Normal)
        LaunchedEffect(key1 = true) {
            delay(5000)
            loadingState.value = AppState.Error
        }


//    navigateTo(SplashNavigation.Main)

}