package com.arya.danesh.myresume.ui.pages.main.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.utilities.state.ToolBarAnimationState


@Composable
fun SubMainBase(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        child: @Composable (LazyListState, LazyListLayoutInfo, (Boolean, Boolean) -> Unit) -> Unit
) {

    val lazyState = rememberLazyListState()
    val visibleItems by remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)





    SideEffect {
        listener(lazyState.isScrollInProgress,
                lazyState.canScrollBackward)
    }
    child(lazyState, visibleItems, listener)
}

