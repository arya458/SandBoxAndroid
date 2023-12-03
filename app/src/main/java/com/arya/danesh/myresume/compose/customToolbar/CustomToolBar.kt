package com.arya.danesh.myresume.compose.customToolbar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.state.ToolBarAnimationState


@Composable
fun CustomToolBar(
    currentPage: String,
    toolBarState: ToolBarAnimationState,
    isAnimationRunningListener: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    val transition = updateTransition(toolBarState, label = "ToolBar State")
    val listener by rememberUpdatedState(isAnimationRunningListener)



    listener(transition.isRunning)


    val rowSize by transition.animateDp(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = if (ToolBarAnimationState.EXPENDED == toolBarState) 50 else 0,
                easing = FastOutSlowInEasing
            )
        }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 260.dp
            ToolBarAnimationState.COLLAPSE -> 45.dp
        }

    }

    val imageSize by transition.animateDp(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 50,
                easing = FastOutSlowInEasing
            )
        }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 100.dp
            ToolBarAnimationState.COLLAPSE -> 30.dp
        }

    }

//    val imageBlur by transition.animateDp(
//        transitionSpec = {
//            tween(
//                durationMillis = 300,
//                delayMillis = 0,
//                easing = FastOutSlowInEasing)
//        }, label = "color"
//
//
//    ) {state ->
//        when(state) {
//            ToolBarAnimationState.EXPENDED -> if (transition.isRunning) 0.dp else 50.dp
//            ToolBarAnimationState.COLLAPSE -> if (transition.isRunning) 0.dp else 50.dp
//            else -> {
//                if (transition.isRunning) 0.dp else 50.dp
//            }
//
//        }
//
//    }

    val columnAlignment by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 50,
                easing = FastOutSlowInEasing
            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0f
            ToolBarAnimationState.COLLAPSE -> 1f

        }


    }

    val textVisibilityExpended by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 50,
                easing = FastOutSlowInEasing
            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0f
            ToolBarAnimationState.COLLAPSE -> 1f
            else -> {
                0f
            }
        }


    }
    val textVisibilityCollapsed by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 50,
                easing = FastOutSlowInEasing
            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 1f
            ToolBarAnimationState.COLLAPSE -> 0f
            else -> {
                1f
            }
        }


    }


//val rowSize by animateDpAsState(
//        targetValue = if (isExpended) 260.dp else 45.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis = 200,
//            easing = FastOutSlowInEasing
//        ), label = ""
//    )
//    val imageSize by animateDpAsState(
//        targetValue = if (isExpended) 100.dp else 30.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis = 50,
//            easing = FastOutSlowInEasing
//        ), label = "",
//    )
//    val imageBlur by animateDpAsState(
//        targetValue = if (isAnimationToolBarFinished) 0.dp else 50.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis = 0,
//            easing = FastOutSlowInEasing
//        ), label = "",)
//
//    val columnAlignment by animateFloatAsState(
//        targetValue = if (!isExpended) 1f else 0f,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis = 50,
//            easing = FastOutSlowInEasing
//        ),
//        label = "",
//
//        finishedListener = {setIsAnimationToolBarFinished(true)}
//        )
//
//    val textVisibilityExpended by animateFloatAsState(
//        targetValue = if (!isExpended) 1f else 0f,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis =50,
//            easing = FastOutSlowInEasing
//        ),
//        label = "",
//
//
//        )
//    val textVisibilityCollapsed by animateFloatAsState(
//        targetValue = if (isExpended) 1f else 0f,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis =50,
//            easing = FastOutSlowInEasing
//        ),
//        label = "",
//
//
//        )
//
//


    Row(

        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .height(rowSize),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (ToolBarAnimationState.COLLAPSE == toolBarState) Text(
            text = currentPage, modifier = Modifier.alpha(textVisibilityExpended),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium,
        )
        Column(
            horizontalAlignment = BiasAlignment.Horizontal(columnAlignment),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.kotlin),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(imageSize, imageSize)
                    .clip(CircleShape)
                    .shadow(5.dp, CircleShape, clip = true)
//                    .blur(if (transition.isRunning) 0.dp else 50.dp)
                    .clickable { onClick() },
            )

            if (ToolBarAnimationState.EXPENDED == toolBarState)
                Text(
                    text = "Arya",
                    modifier = Modifier
                        .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
                        .alpha(textVisibilityCollapsed),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            if (ToolBarAnimationState.EXPENDED == toolBarState)
                Text(
                    text = "TestTestTest",
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                        .alpha(textVisibilityCollapsed),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(0.6f),
                )
            //todo Add ContactUs Buttons (git , Email, Telegram,LikedIn)


        }
    }


}