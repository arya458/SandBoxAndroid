package com.arya.danesh.myresume.ui.core.compose.customToolbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.data.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState


@Composable
fun CustomToolBar(
        modifier: Modifier
) {

    val sharedData: SharedViewModel = viewModel()

    val transition = updateTransition(sharedData.toolBarState.value, label = "ToolBar State")
    val insets = WindowInsetsCompat.Type.systemGestures()
    val rowSize by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = if (sharedData.toolBarState.value == ToolBarAnimationState.COLLAPSE) 200f else 400f,
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

//    val imageSize by transition.animateDp(
//            transitionSpec = {
//
//                spring(
//                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200f else 400f,
//                        dampingRatio = 0.36f,
//                )
////            tween(
////                durationMillis = 300,
////                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 200 else 400,
////                easing = FastOutSlowInEasing
////            )
//            }, label = "color"
//
//    ) { state ->
//
//        when (state) {
//            ToolBarAnimationState.EXPENDED -> 100.dp
//            ToolBarAnimationState.COLLAPSE -> 30.dp
//        }
//
//    }


    val columnAlignment by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = if (sharedData.toolBarState.value == ToolBarAnimationState.COLLAPSE) 400f else 200f,
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
                        stiffness = if (sharedData.toolBarState.value == ToolBarAnimationState.COLLAPSE) 400f else 200f,
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
//    val textVisibilityName by transition.animateFloat(
//            transitionSpec = {
//                spring(
//                        stiffness = if (toolBarState == ToolBarAnimationState.COLLAPSE) 10f else 400f,
//                        dampingRatio = 0.36f,
//                )
////            tween(
////                durationMillis = 300,
////                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
////                easing = FastOutSlowInEasing
////            )
//            }, label = "color"
//
//
//    ) { state ->
//
//        when (state) {
//            ToolBarAnimationState.EXPENDED -> 1f
//            ToolBarAnimationState.COLLAPSE -> 0f
//        }
//
//
//    }


    Row(

            modifier
                    .fillMaxWidth()
                    .padding(top = insets.dp + 10.dp)
                    .requiredHeightIn(min = 45.dp)
                    .height(rowSize),
            verticalAlignment = Alignment.CenterVertically
    ) {
//        if (ToolBarAnimationState.COLLAPSE == toolBarState)
        Column(
                modifier = Modifier
                        .wrapContentWidth()
                        .requiredHeightIn(min = 45.dp)
                        .height(rowSize),
                verticalArrangement = Arrangement.Top,
        )
        {
            Row(
                    Modifier
                            .wrapContentWidth()
                            .height(45.dp), Arrangement.Center, Alignment.CenterVertically) {

//                AlphaAnimText(
//                        text = currentPage,
//                        modifier = Modifier,
//                        isViewOnCollapse = true,
//                        toolBarState = toolBarState
//                )
                Text(
                        text = sharedData.currentPage.value,
                        modifier = Modifier
                                .alpha(textVisibilityTitle)
                                .padding(start = 5.dp, end = 5.dp)
                                .scale(textVisibilityTitle, textVisibilityTitle)
                                .fillMaxWidth(textVisibilityTitle / 2),
                        color = if (android.os.Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
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
            ProfileImage(id = R.drawable.kotlin) {

            }

//            if (ToolBarAnimationState.EXPENDED == toolBarState)
            AlphaAnimText(
                    text = "Aria Danesh",
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp),
                    isViewOnCollapse = false,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
            )
//            Text(
//                    text = "Arya",
//                    modifier = Modifier
//                            .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
//                            .alpha(textVisibilityName)
//                            .wrapContentSize()
//                            .fillMaxWidth(textVisibilityName)
//                            .scale(textVisibilityName, textVisibilityName),
//                    style = MaterialTheme.typography.titleMedium,
//                    textAlign = TextAlign.Center,
//                    color = if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
//            )
//            if (ToolBarAnimationState.EXPENDED == toolBarState)
            AlphaAnimText(
                    text = "TestTestTest",
                    modifier = Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
                    isViewOnCollapse = false,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
            )
//            Text(
//                    text = "TestTestTest",
//                    modifier = Modifier
//                            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
//                            .alpha(textVisibilityName)
//                            .wrapContentSize()
//                            .fillMaxWidth(textVisibilityName)
//                            .scale(textVisibilityName, textVisibilityName),
//                    style = MaterialTheme.typography.titleSmall,
//                    textAlign = TextAlign.Center,
//                    color = (if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground).copy(0.6f),
//            )
            GroupButtons(modifier = Modifier.padding(5.dp).size(40.dp), imagePadding = 10.dp)


        }
    }


}
