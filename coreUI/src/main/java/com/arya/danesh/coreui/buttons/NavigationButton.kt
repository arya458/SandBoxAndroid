package com.arya.danesh.coreui.buttons

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arya.danesh.utilities.state.NavButtonAnimationState


@Composable
fun NavigationButton(
        modifier: Modifier,
        @DrawableRes drawable: Int,
        contentDescription: String?,
        buttonState: NavButtonAnimationState,
        colorFilterDef: ColorFilter? = null,
        onClick: () -> Unit
) {


    val transition = updateTransition(buttonState, label = "Color State")
    val color by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 500,
                delayMillis = 0,
                easing = FastOutSlowInEasing
            )
        }, label = "color"
    )
    {
            state ->

        when (state) {
            NavButtonAnimationState.ACTIVE -> MaterialTheme.colorScheme.primary
            NavButtonAnimationState.DEFAULT -> MaterialTheme.colorScheme.outline
        }
    }

    Surface(
        modifier
            .background(Color.Transparent)
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick() },
        color = Color.Transparent,
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = "NaviButton $contentDescription",
                Modifier
                    .fillMaxSize()
                    .padding(14.dp)
                    .background(Color.Transparent),
                colorFilter = colorFilterDef ?: ColorFilter.tint(color),
                contentScale = ContentScale.Inside

            )

    }

}