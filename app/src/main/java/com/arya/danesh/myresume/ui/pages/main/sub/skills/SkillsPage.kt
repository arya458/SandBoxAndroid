package com.arya.danesh.myresume.ui.pages.main.sub.skills

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.utilities.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.pages.main.component.SubMainBase

@Composable
fun SkillsPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
) {

    SubMainBase(isCollapseListener)
    { lazyState, visibleItems, listener ->

//        LazyColumn(
//                Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
//                state = lazyState,
//
//                ) {
//            items((1..50).toList()) {
//                SkillBigCompose(size = it) { itemNumber ->
//                    if (visibleItems.visibleItemsInfo.isNotEmpty())
//                        if (visibleItems.visibleItemsInfo.first().index <= itemNumber && itemNumber <= visibleItems.visibleItemsInfo.last().index+1)
//                            ComposeItemAnimationState.VISIBLE
//                        else
//                            ComposeItemAnimationState.HIDDEN
//                    else
//                        ComposeItemAnimationState.HIDDEN
//                }
//            }
//        }
    }


}