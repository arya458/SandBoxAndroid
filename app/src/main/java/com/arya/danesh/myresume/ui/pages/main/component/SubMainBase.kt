package com.arya.danesh.myresume.ui.pages.main.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.data.response.SkillResponse
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.utilities.ResourceState
import com.arya.danesh.utilities.state.ToolBarAnimationState


@Composable
fun SubMainBase(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        sharedViewModel: SharedViewModel = hiltViewModel(),
        child: @Composable (LazyListState, LazyListLayoutInfo, titlePadding: Dp, (Boolean, Boolean) -> Unit) -> Unit
) {

    val lazyState = rememberLazyListState()
    val visibleItems by remember { derivedStateOf { lazyState.layoutInfo } }
    val titleSize = remember { mutableStateOf(0.dp) }
    val listener by rememberUpdatedState(isCollapseListener)

    val transition = updateTransition(sharedViewModel.getToolBarState(), label = "ToolBar State")


    val pageTitleAlpha by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = 20f,
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


    SideEffect {
        listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)
    }
//    Column(Modifier.fillMaxSize()) {
    Column(Modifier.fillMaxSize(), Arrangement.Top, Alignment.CenterHorizontally) {
        MenuItemTitle(modifier =
        Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .graphicsLayer {
                    alpha = pageTitleAlpha
                    titleSize.value = size.height.toDp()
                },
                sharedViewModel.getCurrentPage())

    }
    child(lazyState, visibleItems, titleSize.value, listener)


}

