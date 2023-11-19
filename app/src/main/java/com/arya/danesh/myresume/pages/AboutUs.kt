package com.arya.danesh.myresume.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun aboutUs(name: String, currentPage: MutableState<String>, modifier: Modifier = Modifier) {
    currentPage.value=name
    Text(
        text = "AboutUs",
        modifier = modifier
    )
}