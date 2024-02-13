package com.arya.danesh.myresume.ui.core.compose.customToolbar

import android.os.Build
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.core.compose.navigation.NavigationButton
import com.arya.danesh.myresume.ui.core.state.NavButtonAnimationState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState

@Composable
fun CustomToolBarNew(
        modifier: Modifier,
        currentPage: String,
        toolBarState: ToolBarAnimationState,
        isAnimationRunningListener: (Boolean) -> Unit,
        onClick: () -> Unit,
) {

    val transition = updateTransition(toolBarState, label = "ToolBar State")
    val listener by rememberUpdatedState(isAnimationRunningListener)

    val insets = WindowInsetsCompat.Type.systemGestures()
//    val rowSize = remember {
//        Animatable(0.3f)
//    }
//    val imageSize = remember {
//        Animatable(100.dp)
//    }

    listener(transition.isRunning)


    val rootSize by transition.animateDp(
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

    val imageSize by transition.animateDp(
            transitionSpec = {

                spring(
                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 100.dp
            ToolBarAnimationState.COLLAPSE -> 40.dp
        }

    }

    val collapseVisSize by transition.animateDp(
            transitionSpec = {

                spring(
                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0.dp
            ToolBarAnimationState.COLLAPSE -> 50.dp
        }

    }
    val expendedVisSize by transition.animateDp(
            transitionSpec = {

                spring(
                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 30.dp
            ToolBarAnimationState.COLLAPSE -> 0.dp
        }

    }

    val expendedVisAlpha by transition.animateFloat(
            transitionSpec = {

                spring(
                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 1f
            ToolBarAnimationState.COLLAPSE ->0f
        }

    }

    val collapseVisAlpha by transition.animateFloat(
            transitionSpec = {

                spring(
                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
                        dampingRatio = 0.36f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0f
            ToolBarAnimationState.COLLAPSE ->1f
        }

    }


    Column(modifier
            .fillMaxWidth()
            .padding(top = insets.dp + 20.dp, start = 10.dp, end = 10.dp)
            .requiredHeightIn(min = 50.dp)
            .height(rootSize),Arrangement.Center,Alignment.CenterHorizontally) {

        Row(Modifier
                .fillMaxWidth()
                .wrapContentHeight(),Arrangement.SpaceBetween,Alignment.CenterVertically) {

            NavigationButton(
                    modifier = Modifier
                            .alpha(collapseVisAlpha)
                            .size(collapseVisSize),
                    drawable = R.drawable.menu,
                    colorFilterDef = ColorFilter.tint(if (Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground),
                    contentDescription = "Menu",
                    buttonState = NavButtonAnimationState.ACTIVE
            ) {
            }
            Text(
                    text = currentPage,
                    modifier = Modifier
                            .alpha(collapseVisAlpha)
                            .size(collapseVisSize)
                            .wrapContentHeight()
                            .weight(1f, true)

                    ,
                    color = if (Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
            )
            Column(Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),Arrangement.Center,Alignment.CenterHorizontally) {
                Image(
                        painter = painterResource(R.drawable.kotlin),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                                .size(imageSize)
                                .clip(CircleShape)
                                .shadow(5.dp, CircleShape, clip = true)
                                .clickable { onClick() },
                )
                Text(
                        text = "Aria Danesh",
                        modifier = Modifier
                                .size(expendedVisSize)
                                .alpha(expendedVisAlpha)
                                .wrapContentSize(unbounded = toolBarState == ToolBarAnimationState.EXPENDED),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                )
//            if (ToolBarAnimationState.EXPENDED == toolBarState)
                Text(
                        text = "TestTestTest",
                        modifier = Modifier
                                .size(expendedVisSize)
                                .alpha(expendedVisAlpha)
                                .wrapContentSize(unbounded = toolBarState == ToolBarAnimationState.EXPENDED),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        color = (if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground).copy(0.6f),
                )
            }
            Spacer(modifier = Modifier.weight(1f,true))

        }

    }

}