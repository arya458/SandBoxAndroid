package com.arya.danesh.myresume.ui.core.component.customToolbar

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.component.CustomIconButton
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState


@Composable
fun GroupButtons(
        modifier: Modifier = Modifier
                .padding(5.dp)
                .size(50.dp),
        imagePadding: Dp = 14.dp,
        sharedData: SharedViewModel = hiltViewModel()
) {



    val transition = updateTransition(sharedData.getToolBarState(), label = "ToolBar State")

    val visibility by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = if (sharedData.getToolBarState() == ToolBarAnimationState.COLLAPSE) 400f else 200f,
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
            ToolBarAnimationState.EXPENDED -> 1f
            ToolBarAnimationState.COLLAPSE -> 0f
        }


    }


    Row(Modifier.wrapContentSize(), Arrangement.Center, Alignment.CenterVertically) {


        CustomIconButton(modifier = modifier
                .alpha(visibility)
                .scale(visibility)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(15.dp))
                .clickable { },
                imagePadding = imagePadding,
                icon = R.drawable.linkin)
        CustomIconButton(modifier = modifier
                .alpha(visibility)
                .scale(visibility)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(15.dp))
                .clickable { },
                imagePadding = imagePadding,
                icon = R.drawable.email)
        CustomIconButton(modifier = modifier
                .alpha(visibility)
                .scale(visibility)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(15.dp))
                .clickable { },
                imagePadding = imagePadding,
                icon = R.drawable.git)


    }
}