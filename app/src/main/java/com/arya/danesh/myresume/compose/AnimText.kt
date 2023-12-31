package com.arya.danesh.myresume.compose

import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.state.ComposeItemAnimationState


@Composable
fun AnimText(
    rawText: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current,
    animState:ComposeItemAnimationState=ComposeItemAnimationState.HIDDEN,
//    durationMillisForEachChar:Int=10,
    durationMillis:Int=2000,
    delayMillis :Int=500
    ) {


    val transition = updateTransition(animState, label = "Blog State")
//    val durationMillis = durationMillisForEachChar*rawText.length
    val textMaxAnim by transition.animateInt(
        transitionSpec = {
//            spring(
//                stiffness = 10000f,
//                dampingRatio = 0.36f,
//            )
            tween(
                durationMillis = durationMillis,
                delayMillis =delayMillis,
//                easing = FastOutSlowInEasing
            )
        }, label = "color"


    ) { state ->

        when (state) {
            ComposeItemAnimationState.HIDDEN ->0
            ComposeItemAnimationState.VISIBLE -> rawText.length
        }


    }

    val textAnim = rawText.substring(startIndex = 0, endIndex = textMaxAnim)

    Text(
        textAnim,
        modifier,
        color=color,
        fontSize=fontSize,
        fontStyle=fontStyle,
        fontWeight=fontWeight,
        fontFamily=fontFamily,
        letterSpacing=letterSpacing,
        textDecoration=textDecoration,
        textAlign=textAlign,
        lineHeight=lineHeight,
        overflow=overflow,
        softWrap=softWrap,
        maxLines=maxLines,
        minLines=minLines,
        onTextLayout=onTextLayout,
        style=style

        )

}