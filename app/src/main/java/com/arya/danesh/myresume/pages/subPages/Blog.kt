package com.arya.danesh.myresume.pages.subPages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.SplashNavigation
import com.arya.danesh.myresume.compose.Blog
import com.arya.danesh.myresume.state.ComposeItemAnimationState

@Composable
fun BlogPage(
    isCollapseListener: (Boolean, Boolean) -> Unit,
    navigateTo: (SplashNavigation) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val visibleItems by remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    listener(lazyState.isScrollInProgress,lazyState.canScrollBackward)


        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 20.dp, bottom = 70.dp),
            state = lazyState,
        ) {
            items((1..50).toList()) {
                Blog(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),navigateTo
                    ,size = it) { itemNumber ->
                        if (visibleItems.visibleItemsInfo.isNotEmpty())
                            if (visibleItems.visibleItemsInfo.first().index <= itemNumber && itemNumber < visibleItems.visibleItemsInfo.first().index + visibleItems.visibleItemsInfo.size + 1)
                                ComposeItemAnimationState.VISIBLE
                            else
                                ComposeItemAnimationState.HIDDEN
                        else
                            ComposeItemAnimationState.HIDDEN
                    }
            }
        }

}