package com.arya.danesh.myresume.ui.core.compose.customToolbar

import android.support.annotation.DrawableRes
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun ProfileImage(modifier: Modifier = Modifier, @DrawableRes id: Int = R.drawable.kotlin, sharedData: SharedViewModel = hiltViewModel(), onClick: () -> Unit) {

    val configuration = LocalConfiguration.current

    val screenHeight = 300.dp
    val screenWidth = configuration.screenWidthDp.dp


    val transition = updateTransition(sharedData.getToolBarState(), label = "Color State")
    val imageSize by transition.animateDp(
            transitionSpec = {

                spring(
                        stiffness = if (sharedData.getToolBarState() == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 120.dp
            ToolBarAnimationState.COLLAPSE -> 45.dp
            else -> {
                45.dp
            }
        }

    }

    val x by transition.animateDp(
            transitionSpec = {

                spring(
                        stiffness = 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> (screenWidth/2)-(imageSize/2)
            ToolBarAnimationState.COLLAPSE -> screenWidth-imageSize-5.dp
            else -> {
                45.dp
            }
        }

    }
    val y by transition.animateDp(
            transitionSpec = {

                spring(
                        stiffness = 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> (screenHeight/2)-imageSize
            ToolBarAnimationState.COLLAPSE -> 5.dp
            else -> {
                45.dp
            }
        }

    }

    Surface(modifier
            .graphicsLayer {
                this.translationX = x.toPx()
                this.translationY = y.toPx()
            }
            .size(imageSize), color = Color.Transparent) {

        Image(
                painter = painterResource(id),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier

                        .fillMaxSize()
                        .clip(CircleShape)
                        .shadow(elv_3, CircleShape, clip = true)
                        .clickable { onClick() },
        )

////        Column(Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.End) {
//            Surface(Modifier.layout { measurable, constraints ->
//                val placeable = measurable.measure(constraints.copy(constraints.minWidth/6,constraints.minWidth/6,constraints.minHeight/6,constraints.minHeight/6))
//                layout(10,10){
//                    placeable.place(constraints.minWidth/3,constraints.maxHeight/3)
//                }
//
//            }.size(14.dp), color = if (isOnline) Color.Green else Color.Gray, shape = CircleShape, shadowElevation = elv_3) {}
////        }


    }

}