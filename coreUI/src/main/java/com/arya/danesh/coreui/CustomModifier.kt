package com.arya.danesh.coreui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Stable
@Composable
fun Modifier.wrapContentHeightScale(scale: Float): Modifier {
    val size = remember { mutableFloatStateOf(0f) }
    this.wrapContentHeight()
    this.graphicsLayer {
        size.floatValue = this.size.height
    }
    this.height((size.floatValue * scale).dp)
    return this
}
