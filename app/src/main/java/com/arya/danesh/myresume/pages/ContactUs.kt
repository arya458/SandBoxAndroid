package com.arya.danesh.myresume.pages

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun ContactUsPage(
    name: String,
    currentPage: MutableState<String>,
    modifier: Modifier = Modifier,
    lazyState: LazyListState,
) {
    currentPage.value=name
    Text(
        text = "ContactUs",
        modifier = modifier
    )
}