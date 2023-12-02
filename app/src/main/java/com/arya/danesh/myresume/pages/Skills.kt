package com.arya.danesh.myresume.pages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.compose.views.SkillBig

@Composable
fun SkillsPage(
    modifier: Modifier = Modifier,
    lazyState: LazyListState,
) {

    val visibleItems = remember { derivedStateOf { lazyState.layoutInfo } }



    Surface(Modifier
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
                SkillBig(
                    visibleItems.value.visibleItemsInfo.size,
                    visibleItems.value.visibleItemsInfo.first().index,
                    it
                )
            }
        }
    }

}