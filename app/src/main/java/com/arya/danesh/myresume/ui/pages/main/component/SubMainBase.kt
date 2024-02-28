package com.arya.danesh.myresume.ui.pages.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier


@Composable
fun SubMainBase(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        child: @Composable (LazyListState, LazyListLayoutInfo, (Boolean, Boolean) -> Unit) -> Unit
) {

    val lazyState = rememberLazyListState()
    val visibleItems by remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    SideEffect {
        listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)
    }
    child(lazyState, visibleItems, listener)
}

