package com.sandbox.sandboxMessenger.ui.pages.authorization

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.controller.route.RootNavigation
import com.sandbox.sandboxMessenger.di.viewModels.MessengerViewModel
import com.sandbox.sandboxMessenger.ui.pages.authorization.component.AuthBase
import net.folivo.trixnity.clientserverapi.client.UIA


@Composable
@PreviewScreenSizes
fun RegisterPage(navigateTo: (RootNavigation) -> Unit = {}, messengerViewModel: MessengerViewModel = hiltViewModel()) {

    val user = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val againPass = remember { mutableStateOf("") }

//    val email = remember { mutableStateOf("") }


    val isError = remember { mutableStateOf(false) }

    val regStage by messengerViewModel.registerStage.collectAsState()

//    val verifyRegStage by messengerViewModel.verifyRegisterStage.collectAsState()


    AuthBase {
//        val coroutineScope = rememberCoroutineScope()

        Row(it.fillMaxSize(), Arrangement.Center, Alignment.CenterVertically) {

//            Spacer(modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.1f))

            when (regStage) {

                null -> {

                    Column(modifier = Modifier
                            .fillMaxSize()
                            .weight(0.6f),
                            Arrangement.Center, Alignment.CenterHorizontally
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
                        TextField(
                                modifier = Modifier
                                        .padding(5.dp)
                                        .width(300.dp)
                                        .clip(RoundedCornerShape(15.dp))
                                        .wrapContentHeight(),
                                value = againPass.value,
                                onValueChange = { againPass.value = it },
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
                                modifier = Modifier
                                        .padding(top = 50.dp)
                                        .width(200.dp)
                                        .wrapContentHeight(),
                                onClick = {
//                                    if (againPass.value.equals(pass.value))
                                    messengerViewModel.register(user.value, pass.value, null)

//                            navigateTo(RootNavigation.Root.MainPage)
                                },
                                shape = RoundedCornerShape(15.dp),
                                colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary,
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
//                        ElevatedButton(
//                                modifier = Modifier
//                                        .padding(top = 10.dp)
//                                        .width(200.dp)
//                                        .wrapContentHeight() ,
//                                onClick = { navigateTo(RootNavigation.Root.Register) },
//                                shape = RoundedCornerShape(15.dp),
//                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//                                colors = ButtonDefaults.buttonColors(
//                                        containerColor = MaterialTheme.colorScheme.background,
//                                        contentColor = MaterialTheme.colorScheme.primary,
//                                        disabledContainerColor = Color.DarkGray,
//                                        disabledContentColor = Color.Gray
//                                )
//                        ) {
//                            Text(
//                                    text = "Register",
//                                    modifier = Modifier
//                                            .fillMaxWidth()
//                                            .align(Alignment.CenterVertically)
//                                            .wrapContentHeight(),
//                                    textAlign = TextAlign.Center,
//                                    style = MaterialTheme.typography.titleMedium,
//                            )
//                        }

                    }

                }

                is UIA.Success -> {
                    val data = (regStage as UIA.Success)
                    Log.d("RegisterPage", "RegisterPage: Success")
                    Log.d("RegisterPage", "RegisterPage: $data")
                    navigateTo(RootNavigation.Root.MainPage)
                }

                /** We Are Registering Users as AccountType.GUEST So We Don't Need Any Verification **/
                is UIA.Step -> {
//                    val data = (regStage as UIA.Step)
//                    val state = data.state
//                    val context = LocalContext.current
//                    val parameter = state.parameter
//                    val steps = data.state.flows.first().stages
//                    val response by messengerViewModel.registerStepResponse.collectAsState()
//
//                    if (verifyRegStage == null)
//                        Column(modifier = Modifier
//                                .fillMaxSize()
//                                .weight(0.6f),
//                                Arrangement.Center, Alignment.CenterHorizontally
//                        ) {
//
//                            TextField(
//                                    modifier = Modifier
//                                            .padding(5.dp)
//                                            .width(300.dp)
//                                            .clip(RoundedCornerShape(15.dp))
//                                            .wrapContentHeight(),
//                                    value = email.value,
//                                    onValueChange = { email.value = it },
//                                    maxLines = 3,
//                                    placeholder = { Text(text = "Email", color = MaterialTheme.colorScheme.onBackground.copy(0.6f)) },
//                                    singleLine = true,
//                                    enabled = true,
//                                    colors = TextFieldDefaults.colors(
//                                            unfocusedIndicatorColor = Color.Transparent,
//                                            focusedIndicatorColor = Color.Transparent,
//                                    ),
//                                    isError = isError.value,
//                            )
//
//                            SideEffect {
//                                coroutineScope.launch {
//                                }
//                            }
//
//                            ElevatedButton(
//                                    modifier = Modifier
//                                            .padding(top = 50.dp)
//                                            .width(200.dp)
//                                            .wrapContentHeight(),
//                                    onClick = {
////                                        messengerViewModel.recaptchaVerify(context)
//                                        if (response!=null)
//                                        if (response!!.recaptcha!=null)
//                                        if (response!!.recaptcha!!.publicKey!=null)
//                                        SafetyNet.getClient(context).verifyWithRecaptcha("6Ld1qpspAAAAAOSFPnwpotizLWIJSqYuJJsBkJr5").addOnSuccessListener {
//                                            if (it.tokenResult!!.isNotEmpty()) {
//                                                // calling handle verification method
//                                                // to handle verification.
////                                                handleVerification(it.tokenResult.toString(), ctx)
//
//                                                Log.d(
//                                                        "test", "t: " +
//                                                        it.tokenResult.toString()
//                                                )
//                                            }
//                                        }.addOnFailureListener {
//                                            // on below line handling exception
//                                            if (it is ApiException) {
//                                                val apiException = it as ApiException
//                                                // below line is use to display an
//                                                // error message which we get.
//                                                Log.d(
//                                                        "test", "Error message: " +
//                                                        CommonStatusCodes.getStatusCodeString(apiException.statusCode)
//                                                )
//                                            } else {
//                                                // below line is use to display a toast message for any error.
//                                                Toast.makeText(context, "Error found is : $it", Toast.LENGTH_SHORT)
//                                                        .show()
//                                            }
//                                        }
//
//
////                                        if (state.session != null)
////                                            messengerViewModel.emailVerify(state.session!!, email.value)
//
//
//                                    },
//                                    shape = RoundedCornerShape(15.dp),
//                                    colors = ButtonDefaults.buttonColors(
//                                            containerColor = MaterialTheme.colorScheme.primary,
//                                            contentColor = MaterialTheme.colorScheme.onPrimary,
//                                            disabledContainerColor = Color.DarkGray,
//                                            disabledContentColor = Color.Gray
//                                    )
//                            ) {
//                                Text(
//                                        text = "Verify",
//                                        modifier = Modifier
//                                                .fillMaxWidth()
//                                                .align(Alignment.CenterVertically)
//                                                .wrapContentHeight(),
//                                        textAlign = TextAlign.Center,
//                                        style = MaterialTheme.typography.titleMedium,
//                                )
//                            }
//                        }
//                    else {
//                        if (verifyRegStage!!.isSuccess) {
//                            Text(
//                                    text = "Check Your Email Please",
//                                    modifier = Modifier
//                                            .fillMaxWidth()
//                                            .align(Alignment.CenterVertically)
//                                            .wrapContentHeight(),
//                                    textAlign = TextAlign.Center,
//                                    style = MaterialTheme.typography.titleMedium,
//                            )
//                            ElevatedButton(
//                                    modifier = Modifier
//                                            .padding(top = 50.dp)
//                                            .width(200.dp)
//                                            .wrapContentHeight(),
//                                    onClick = {
//                                        navigateTo(RootNavigation.Root.Login)
//                                    },
//                                    shape = RoundedCornerShape(15.dp),
//                                    colors = ButtonDefaults.buttonColors(
//                                            containerColor = MaterialTheme.colorScheme.primary,
//                                            contentColor = MaterialTheme.colorScheme.onPrimary,
//                                            disabledContainerColor = Color.DarkGray,
//                                            disabledContentColor = Color.Gray
//                                    )
//                            ) {
//                                Text(
//                                        text = "Login",
//                                        modifier = Modifier
//                                                .fillMaxWidth()
//                                                .align(Alignment.CenterVertically)
//                                                .wrapContentHeight(),
//                                        textAlign = TextAlign.Center,
//                                        style = MaterialTheme.typography.titleMedium,
//                                )
//                            }
//                        }
//                        if (verifyRegStage!!.isFailure)
//                            Text(
//                                    text = "Something Went Wrong Please Try Again",
//                                    modifier = Modifier
//                                            .fillMaxWidth()
//                                            .align(Alignment.CenterVertically)
//                                            .wrapContentHeight(),
//                                    textAlign = TextAlign.Center,
//                                    style = MaterialTheme.typography.titleMedium,
//                            )
//                    }

                }

                is UIA.Error -> {
                    Log.d("RegisterPage", "RegisterPage: Error")
                }


            }


//            Spacer(modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.1f))


        }

    }


}
