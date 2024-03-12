package com.arya.danesh.coreui

import android.os.Build
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.arya.danesh.utilities.state.AppState


@Composable
fun Background(
//        sharedData: SharedViewModel = hiltViewModel()
        @androidx.annotation.RawRes resId: Int
){


//    val appTransition = updateTransition(sharedData.getAppState(), label = "App State")
//
//
//
//    val speedAnim by appTransition.animateFloat(
//            transitionSpec = {
//                spring(
//                        stiffness = 200f,
//                        dampingRatio = 0.36f,
//                )
////            tween(
////                durationMillis = 300,
////                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
////                easing = FastOutSlowInEasing
////            )
//            }, label = "color"
//
//
//    ) { state ->
//
//        when (state) {
//            AppState.Normal -> 0.5f
//            AppState.Update -> 1f
//            AppState.Error -> 2f
//        }
//
//
//    }
//
//    val colorAnim by appTransition.animateColor(
//            transitionSpec = {
//                spring(
//                        stiffness = 200f,
//                        dampingRatio = 0.36f,
//                )
////            tween(
////                durationMillis = 300,
////                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
////                easing = FastOutSlowInEasing
////            )
//            }, label = "color"
//
//
//    ) { state ->
//        when (state) {
//            AppState.Normal -> MaterialTheme.colorScheme.primary
//            AppState.Update -> MaterialTheme.colorScheme.secondary
//            AppState.Error -> MaterialTheme.colorScheme.error
//        }
//
//
//    }


//R.raw.bg

    val composition3 by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                    property = LottieProperty.COLOR,
//                    value = colorAnim.toArgb(),
                    value = MaterialTheme.colorScheme.primary.toArgb(),
                    keyPath = arrayOf(
                            "**"
                    )
            )
    )








    Surface(
            Modifier
                    .fillMaxSize()) {
        if (Build.VERSION.SDK_INT > 31) {
            LottieAnimation(
                    composition = composition3,
                    modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .blur(150.dp, BlurredEdgeTreatment.Unbounded),
                    iterations = LottieConstants.IterateForever,
                    dynamicProperties = dynamicProperties,
                    contentScale = ContentScale.FillBounds,
                    reverseOnRepeat = true,
//                    speed = speedAnim
                    speed = 0.5f

            )
        }else
            Surface(
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

            ) {

            }
    }

}