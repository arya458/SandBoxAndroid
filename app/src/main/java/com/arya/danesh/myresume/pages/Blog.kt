package com.arya.danesh.myresume.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.pages.sections.views.Blog

@Composable
fun blog(name: String, currentPage: MutableState<String>, modifier: Modifier = Modifier) {
    currentPage.value = name
    LazyColumn(Modifier.padding(bottom = 60.dp).fillMaxSize()) {
        items((1..500).toList()) {
            Blog(Modifier.wrapContentHeight().fillMaxWidth())
        }
    }
}