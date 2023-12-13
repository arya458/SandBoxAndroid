package com.arya.danesh.myresume.compose.customToolbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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

//    val rowSize = remember {
//        Animatable(0.3f)
//    }
//    val imageSize = remember {
//        Animatable(100.dp)
//    }

    listener(transition.isRunning)


    val rowSize by transition.animateDp(
        transitionSpec = {
            spring(
                stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                dampingRatio = 0.46f,
            )
        }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 300.dp
            ToolBarAnimationState.COLLAPSE -> 45.dp
        }

    }

//    val imageYTransition by transition.animateOffset(
//
//        transitionSpec = {
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200 else 300,
//                easing = FastOutSlowInEasing
//            )
//        }, label = "color"
//
//    ){ state ->
//        when (state) {
//            ToolBarAnimationState.EXPENDED -> 0.5f
//            ToolBarAnimationState.COLLAPSE -> 0.0f
//        }
//    }

    val imageSize by transition.animateDp(
        transitionSpec = {

            spring(
                stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200 else 400,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 100.dp
            ToolBarAnimationState.COLLAPSE -> 30.dp
        }

    }


    val columnAlignment by transition.animateFloat(
        transitionSpec = {
            spring(
                stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 400f else 200f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 400 else 0,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0f
            ToolBarAnimationState.COLLAPSE -> 1f

        }


    }

    val textVisibilityTitle by transition.animateFloat(
        transitionSpec = {
            spring(
                stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 400f else 200f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 400 else 0,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0f
            ToolBarAnimationState.COLLAPSE -> 1f
        }


    }
    val textVisibilityName by transition.animateFloat(
        transitionSpec = {
            spring(
                stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 10f else 400f,
                dampingRatio = 0.36f,
            )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
//                easing = FastOutSlowInEasing
//            )
        }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 1f
            ToolBarAnimationState.COLLAPSE -> 0f
        }


    }


    Row(

        Modifier
            .fillMaxWidth()
            .requiredHeightIn(min = 45.dp)
            .height(rowSize)
        
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        if (ToolBarAnimationState.COLLAPSE == toolBarState)
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .requiredHeightIn(min = 45.dp)
                .height(rowSize)
            ,
            verticalArrangement = Arrangement.Top,
        )
                {
                    Row(
                        Modifier
                            .wrapContentWidth()
                            .height(45.dp),Arrangement.Center,Alignment.CenterVertically) {
                        Text(
                            text = currentPage,
                            modifier = Modifier
                                .alpha(textVisibilityTitle)
                                .padding(start = 5.dp, end = 5.dp)
                                .scale(textVisibilityTitle, textVisibilityTitle)
                                .fillMaxWidth(textVisibilityTitle / 2)
                            ,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Justify,
                            )
                    }
                }
        Column(
            horizontalAlignment = BiasAlignment.Horizontal(columnAlignment),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize()
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
                    .clickable { onClick() },
            )

//            if (ToolBarAnimationState.EXPENDED == toolBarState)
            Text(
                text = "Arya",
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
                    .alpha(textVisibilityName)
                    .wrapContentSize()
                    .fillMaxWidth(textVisibilityName)
                    .scale(textVisibilityName, textVisibilityName)
                ,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
            )
//            if (ToolBarAnimationState.EXPENDED == toolBarState)
            Text(
                text = "TestTestTest",
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                    .alpha(textVisibilityName)
                    .wrapContentSize()
                    .fillMaxWidth(textVisibilityName)
                    .scale(textVisibilityName, textVisibilityName),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary.copy(0.6f),
            )
            //todo Add ContactUs Buttons (git , Email, Telegram,LikedIn)


        }
    }


}