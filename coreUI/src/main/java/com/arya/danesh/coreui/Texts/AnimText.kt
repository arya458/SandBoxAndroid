package com.arya.danesh.coreui.Texts

import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.arya.danesh.utilities.state.ComposeItemAnimationState


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
        setAnimState :(animState: MutableState<ComposeItemAnimationState>) -> Unit,
        durationMillis:Int=2000,
        delayMillis :Int=500
    ) {
    val animState = remember { mutableStateOf(ComposeItemAnimationState.HIDDEN) }
    setAnimState(animState)
    val transition = updateTransition(animState.value, label = "Blog State")
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

