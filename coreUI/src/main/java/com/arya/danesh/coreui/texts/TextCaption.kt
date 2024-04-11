package com.arya.danesh.coreui.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TextCaption(modifier: Modifier,
               text:String,
               color : Color=MaterialTheme.colorScheme.onSurface,
               style : TextStyle = MaterialTheme.typography.bodySmall,
               textAlign : TextAlign = TextAlign.Justify,
) {

    Text(
            text = text,
            modifier = modifier,
            color = color,
            style = style,
            textAlign = textAlign,
    )

}