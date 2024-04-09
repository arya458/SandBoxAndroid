package com.arya.danesh.coreui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.arya.danesh.coreui.Texts.TextTittle
import com.arya.danesh.utilities.CoreUtility.manipulateColor


@Composable
fun SubLoadingPage(isDark:Boolean){

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,Alignment.CenterHorizontally) {

//        LinearProgressIndicator(Modifier
//                .fillMaxWidth(0.5f)
//                .padding(bottom = 10.dp)
//                .height(2.dp)
//                .shadow(elv_3)
//        )
        val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data_loading1))
        val dynamicProperties =
                rememberLottieDynamicProperties(
                rememberLottieDynamicProperty(
                        property = LottieProperty.COLOR,
                        value = manipulateColor(MaterialTheme.colorScheme.primary.toArgb(),1f,isDark),
                        keyPath = arrayOf(
                                "**","Shape Layer 3","**"
                        )),
                rememberLottieDynamicProperty(
                        property = LottieProperty.COLOR,
                        value = manipulateColor(MaterialTheme.colorScheme.primary.toArgb(),2f,isDark),
                        keyPath = arrayOf(
                                "**","Shape Layer 2","**"
                        )),
                rememberLottieDynamicProperty(
                        property = LottieProperty.COLOR,
                        value = manipulateColor(MaterialTheme.colorScheme.primary.toArgb(),1.5f,isDark),
                        keyPath = arrayOf(
                                "**","Shape Layer 1","**"
                        )),
        )
        LottieAnimation(
                composition = composition1,
                modifier =
                Modifier.size(80.dp),
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Inside,
                dynamicProperties = dynamicProperties,
                reverseOnRepeat = true,
                speed = 1f

        )
        Spacer(modifier = Modifier.size(10.dp))
        TextTittle(
                modifier = Modifier.fillMaxWidth(),
                text = "Loading ...",
                color =MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
        )



    }

}