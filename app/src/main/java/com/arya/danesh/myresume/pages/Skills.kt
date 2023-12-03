package com.arya.danesh.myresume.pages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.compose.SkillBig
import com.arya.danesh.myresume.state.ComposeItemAnimationState

@Composable
fun SkillsPage(
    isCollapseListener: (Boolean, Boolean) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val visibleItems by remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)


    Surface(
        Modifier
//        .shadow((-20).dp, clip = true)
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
//        elevation = (-5).dp,
        color = MaterialTheme.colorScheme.background,
    ) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 20.dp, bottom = 70.dp),
            state = lazyState,

            ) {
            items((1..50).toList()) {
                SkillBig(size = it) { itemNumber ->
                    if (visibleItems.visibleItemsInfo.isNotEmpty())
                        if (visibleItems.visibleItemsInfo.first().index <= itemNumber && itemNumber < visibleItems.visibleItemsInfo.first().index + visibleItems.visibleItemsInfo.size+1)
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