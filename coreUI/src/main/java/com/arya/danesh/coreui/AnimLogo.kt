package com.arya.danesh.coreui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
//import com.arya.danesh.myresume.ui.theme.elv_3

//R.drawable.icon
//R.raw.new_loading_anim
@Composable
fun AnimLogo(
        size: Dp,
        color: Color = MaterialTheme.colorScheme.primary,
        animSpeed:Float=0.2f,
        shadowElevation: Dp = 0.dp,
        @DrawableRes logoID : Int,
        @androidx.annotation.RawRes lottieAnimationID : Int) {


    val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnimationID))
//    val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.new_loading_anim))
//    val composition3 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.new_loading_anim))


    val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                    property = LottieProperty.COLOR,
                    value = color.toArgb(),
                    keyPath = arrayOf(
                            "**"
                    )),


            )

    Box(
            Modifier.size(size).background(Color.Transparent),
            contentAlignment = Alignment.Center) {
        LottieAnimation(
                composition = composition1,
                modifier =
                        Modifier.size(size)
                        .alpha(0.35f),
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds,
                dynamicProperties = dynamicProperties,
                reverseOnRepeat = true,
                speed = animSpeed

        )
        LottieAnimation(
                composition = composition1,
                modifier = Modifier.size(size).padding(10.dp)
                        .alpha(0.35f),
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds,

                dynamicProperties = dynamicProperties,
                reverseOnRepeat = true,
                speed = animSpeed+0.1f

        )
        LottieAnimation(
                composition = composition1,
                modifier =Modifier.size(size).padding(20.dp)
                        .alpha(0.35f),
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds,

                dynamicProperties = dynamicProperties,
                reverseOnRepeat = true,
                speed = animSpeed+0.3f

        )
        Surface(Modifier.size(size/2),
                shape = CircleShape,
                shadowElevation = shadowElevation,
                color = MaterialTheme.colorScheme.surface) {
            Image(
                    painter = painterResource(logoID),
                    contentDescription = "LoadingPass",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                            .padding(20.dp)


            )
        }
    }

}