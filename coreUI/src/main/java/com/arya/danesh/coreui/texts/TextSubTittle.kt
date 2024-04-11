package com.arya.danesh.coreui.texts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TextSubTittle(modifier: Modifier,
                  text:String,
                  color : Color=MaterialTheme.colorScheme.onSurface,
                  style : TextStyle = MaterialTheme.typography.bodyMedium,
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


@Preview
@Composable
fun testText(){
    Column(Modifier.fillMaxSize()) {
        TextTittle(modifier = Modifier.fillMaxWidth(), text = "Tittle")
        TextSubTittle(modifier = Modifier.fillMaxWidth(), text = "SubTittle")
        TextCaption(modifier = Modifier.fillMaxWidth(), text = "Caption")
    }

}