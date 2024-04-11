package com.arya.danesh.myresume.ui.pages.main.sub.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.SubLoadingPage
import com.arya.danesh.myresume.ui.pages.main.component.SubMainBase


@Composable
fun HomePage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
) {
    SubMainBase(isCollapseListener)
    { lazyState, visibleItems, listener ->

        SubLoadingPage(isDark = isSystemInDarkTheme())

    }


}