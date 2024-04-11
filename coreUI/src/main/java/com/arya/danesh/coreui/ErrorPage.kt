package com.arya.danesh.coreui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.texts.TextSubTittle
import com.arya.danesh.coreui.texts.TextTittle


@Composable
fun ErrorPage(errorText: String, tryAgain: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally) {

        TextTittle(
                modifier = Modifier.fillMaxWidth(),
                text = "Error",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextSubTittle(
                modifier = Modifier.fillMaxWidth(),
                text = errorText,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = { tryAgain() }, colors =
        ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
        )) {
            TextSubTittle(Modifier.wrapContentSize(), text = "TryAgain", color = MaterialTheme.colorScheme.onPrimary)
        }


    }
}