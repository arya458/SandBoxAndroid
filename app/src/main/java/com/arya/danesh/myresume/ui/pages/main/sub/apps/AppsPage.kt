package com.arya.danesh.myresume.ui.pages.main.sub.apps

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.pages.main.component.SubMainBase

@Composable
fun AppsPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
) {
    SubMainBase(isCollapseListener)
    { lazyState, visibleItems, listener ->
        Text(
                text = "AboutUs",
        )
    }

}