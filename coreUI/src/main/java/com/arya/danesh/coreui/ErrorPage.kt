package com.arya.danesh.coreui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.Texts.TextTittle
import com.arya.danesh.coreui.theme.elv_3


@Composable
fun ErrorPage(errorText:String,tryAgain:()->Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally) {

        TextTittle(
                modifier = Modifier.fillMaxWidth(),
                text = "Error",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextTittle(
                modifier = Modifier.fillMaxWidth(),
                text = errorText,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = { tryAgain() }) {
            Text(text = "TryAgain")
        }


    }
}