package com.sandbox.sandboxMessenger.ui.pages.authorization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.arya.danesh.controller.route.RootNavigation
import com.sandbox.sandboxMessenger.ui.pages.authorization.component.AuthBase

@Composable
@PreviewScreenSizes
fun LoginPage(navigateTo: (RootNavigation) -> Unit = {}) {

    val user = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }


    AuthBase {

        Row(it.fillMaxSize(), Arrangement.Center, Alignment.CenterVertically) {

//            Spacer(modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.1f))
            Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(0.6f),
                    Arrangement.Center,Alignment.CenterHorizontally
            ) {
                TextField(
                        modifier = Modifier
                                .padding(5.dp)
                                .width(300.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .wrapContentHeight(),
                        value = user.value,
                        onValueChange = { user.value = it },
                        maxLines = 3,
                        placeholder = { Text(text = "UserName", color = MaterialTheme.colorScheme.onBackground.copy(0.6f)) },
                        singleLine = true,
                        enabled = true,
                        colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                        ),
                        isError = isError.value,
                )
                TextField(
                        modifier = Modifier
                                .padding(5.dp)
                                .width(300.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .wrapContentHeight(),
                        value = pass.value,
                        onValueChange = { pass.value = it },
                        maxLines = 3,
                        placeholder = { Text(text = "PassWord", color = MaterialTheme.colorScheme.onBackground.copy(0.6f)) },
                        singleLine = true,
                        enabled = true,
                        colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                        ),
                        isError = isError.value,
                )


                ElevatedButton(
                        modifier =Modifier
                                .padding(top = 50.dp)
                                .width(200.dp)
                                .wrapContentHeight() ,
                        onClick = { navigateTo(RootNavigation.Root.MainPage) },
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                disabledContainerColor = Color.DarkGray,
                                disabledContentColor = Color.Gray
                        )
                        ) {
                    Text(
                            text = "Login",
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                                    .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                    )
                }
                ElevatedButton(
                        modifier =Modifier
                                .padding(top = 10.dp)
                                .width(200.dp)
                                .wrapContentHeight() ,
                        onClick = { navigateTo(RootNavigation.Root.Register) },
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
                        colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.primary,
                                disabledContainerColor = Color.DarkGray,
                                disabledContentColor = Color.Gray
                        )
                ) {
                    Text(
                            text = "Register",
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                                    .wrapContentHeight(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                    )
                }

            }
//            Spacer(modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.1f))


        }


//        TextField(
//                modifier = Modifier
//                        .weight(1f,true)
//                        .wrapContentHeight()
//                ,
//                value = userMessage,
//                onValueChange = {
//                    setUserMessage(it)
//                },
//                maxLines = 3,
//                placeholder = { Text(text = "Message...", color = MaterialTheme.colorScheme.onBackground.copy(0.6f)) },
//                singleLine = false,
//                enabled = true,
//                colors = TextFieldDefaults.colors(),
//                isError = false,
//        )

    }


}