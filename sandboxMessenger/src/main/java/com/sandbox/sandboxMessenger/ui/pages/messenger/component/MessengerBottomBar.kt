package com.sandbox.sandboxMessenger.ui.pages.messenger.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.buttons.NavigationButton
import com.arya.danesh.coreui.texts.TextCaption
import com.arya.danesh.coreui.theme.elv_2
import com.arya.danesh.coreui.theme.elv_3
import com.arya.danesh.utilities.state.NavButtonAnimationState
import com.sandbox.sandboxmessenger.R


@Composable
fun MessengerBottomBar(userMessage : String,setUserMessage : (text:String)->Unit,onclick:()->Unit) {

    Card(
            modifier = Modifier
                    .padding(bottom = WindowInsets.navigationBars
                            .asPaddingValues()
                            .calculateBottomPadding())
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom, unbounded = true)
                    .padding(end = 10.dp, start = 10.dp),
            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
            colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(
                    pressedElevation = elv_2,
                    defaultElevation = elv_3
            ),
    ) {

        Row(
                Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                Arrangement.End,
                Alignment.CenterVertically) {
            TextField(
                    modifier = Modifier
                            .weight(1f, true)
                            .wrapContentHeight()
                    ,
                    value = userMessage,
                    onValueChange = {
                        setUserMessage(it)
                    },
                    maxLines = 3,
                    placeholder = { TextCaption(Modifier.fillMaxWidth(),text = "Message...", color = MaterialTheme.colorScheme.onBackground.copy(0.6f)) },
                    singleLine = false,
                    enabled = true,
                    colors = TextFieldDefaults.colors(),
                    isError = false,
            )
            NavigationButton(
                    modifier = Modifier
                            .size(50.dp)
                            .padding(0.dp),
                    drawable = R.drawable.send,
                    contentDescription = "Send",
                    buttonState = NavButtonAnimationState.ACTIVE
            ) {
                onclick()
            }

        }

//            }


    }

}