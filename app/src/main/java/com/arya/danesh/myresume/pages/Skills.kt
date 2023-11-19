package com.arya.danesh.myresume.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.pages.sections.views.SkillBig

@Composable
fun skills(name: String, currentPage: MutableState<String>, modifier: Modifier = Modifier) {
    currentPage.value = name
    LazyColumn(Modifier.padding(bottom = 60.dp).fillMaxSize()) {
        items((1..500).toList()) {
            SkillBig()
        }
    }

}