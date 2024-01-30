package com.arya.danesh.myresume.ui.pages.subPages.messenger

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.arya.danesh.myresume.ui.controller.SplashNavigation

@Composable
fun AiChatPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (SplashNavigation) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val visibleItems = remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)

    Text(
        text = "ContactUs",
    )

}