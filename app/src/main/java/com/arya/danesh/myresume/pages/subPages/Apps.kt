package com.arya.danesh.myresume.pages.subPages

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.arya.danesh.myresume.SplashNavigation

@Composable
fun AppsPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (SplashNavigation) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val visibleItems = remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)

    Text(
        text = "AboutUs",
    )

}