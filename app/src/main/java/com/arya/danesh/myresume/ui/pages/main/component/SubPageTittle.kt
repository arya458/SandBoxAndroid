package com.arya.danesh.myresume.ui.pages.main.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.size.Size
import com.arya.danesh.coreui.wrapContentHeightScale
import com.arya.danesh.utilities.state.ToolBarAnimationState

@Composable
fun SubPageTittle(text: String, toolBarState: ToolBarAnimationState) {

    val transition = updateTransition(toolBarState, label = "ToolBar State")
    var tittleSize = 0.dp

    val pageTitleAlpha by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = 40f,
                        dampingRatio = 0.46f,
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

    Column(Modifier
            .fillMaxWidth()
            .height((70 * pageTitleAlpha).toInt().dp)
            .scale(pageTitleAlpha)
            , Arrangement.Top, Alignment.CenterHorizontally) {
        MenuItemTitle(
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeightScale(pageTitleAlpha)
                        .graphicsLayer {
                            alpha = pageTitleAlpha
                        },
                text = text,

                )

    }
}