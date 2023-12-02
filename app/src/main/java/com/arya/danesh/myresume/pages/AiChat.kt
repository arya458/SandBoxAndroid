package com.arya.danesh.myresume.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun AiChatPage(
    modifier: Modifier = Modifier,
    lazyState: LazyListState,
) {
    Surface(Modifier
//        .shadow((-20).dp, clip = true)
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
//        elevation = (-5).dp,
        color = MaterialTheme.colorScheme.background,
    ) {
        Text(
            text = "ContactUs",
            modifier = modifier
        )
    }
}