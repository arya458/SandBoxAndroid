package com.arya.danesh.myresume.ui.pages.main.sub.home

import androidx.compose.runtime.Composable
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.pages.main.compose.SubMainBase

//import com.arya.danesh.myresume.ui.theme.section
//import com.arya.danesh.myresume.ui.theme.title

@Composable
fun HomePage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
) {
    SubMainBase(isCollapseListener)
    { lazyState, visibleItems, listener ->



    }


}