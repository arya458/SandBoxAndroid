package com.arya.danesh.myresume.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.pages.sections.views.SkillBig

@Composable
fun SkillsPage(
    name: String,
    currentPage: MutableState<String>,
    modifier: Modifier = Modifier,
    lazyState: LazyListState,
) {
    currentPage.value = name

    LazyColumn(
        Modifier.padding(bottom = 60.dp).fillMaxSize(),
        state=lazyState,

    ) {
        items((1..500).toList()) {
            SkillBig()
        }
    }

}