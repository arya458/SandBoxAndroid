package com.arya.danesh.myresume.ui.pages.subPages.skills

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.arya.danesh.myresume.ui.controller.SplashNavigation
import com.arya.danesh.myresume.ui.pages.subPages.skills.compose.SkillBig
import com.arya.danesh.myresume.ui.core.state.ComposeItemAnimationState

@Composable
fun SkillsPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (SplashNavigation) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val visibleItems by remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)



    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
        state = lazyState,

        ) {
        items((1..50).toList()) {
            SkillBig(size = it) { itemNumber ->
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