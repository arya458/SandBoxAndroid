package com.arya.danesh.myresume.ui.pages.main.sub.blog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.core.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.pages.main.compose.SubMainBase
import com.arya.danesh.myresume.ui.pages.main.sub.blog.compose.BlogCompose

@Composable
fun BlogPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
) {
    SubMainBase(isCollapseListener)
    {
        lazyState,visibleItems,listener ->
        LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
                state = lazyState,
        ) {
            items((1..50).toList()) {
                BlogCompose(navigateTo,size = it) { itemNumber ->
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


}