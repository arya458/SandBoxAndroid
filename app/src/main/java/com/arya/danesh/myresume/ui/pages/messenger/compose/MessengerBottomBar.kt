package com.arya.danesh.myresume.ui.pages.messenger.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.core.compose.navigation.NavigationButton
import com.arya.danesh.myresume.ui.core.state.NavButtonAnimationState
import com.arya.danesh.myresume.ui.theme.elv_2


@Composable
fun MessengerBottomBar(userMessage : String,setUserMessage : (text:String)->Unit) {

    val insets = WindowInsetsCompat.Type.systemGestures() * 2
    Card(
            Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom,unbounded = true)
                    .padding(bottom = insets.dp, end = 10.dp, start = 10.dp),
            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            elevation = elv_2,
    ) {

        Row(Modifier.fillMaxWidth().wrapContentHeight(),Arrangement.End,Alignment.CenterVertically) {
            TextField(
                    modifier = Modifier
                            .weight(1f,true)
                            .wrapContentHeight()
                    ,
                    value = userMessage,
                    onValueChange = {
                        setUserMessage(it)
                    },
                    maxLines = 3,
                    placeholder = { Text(text = "Message...", color = MaterialTheme.colorScheme.onBackground.copy(0.6f)) },
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
            }

        }

//            }


    }

}