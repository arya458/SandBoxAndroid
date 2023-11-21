package com.arya.danesh.myresume.pages.sections.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun NavigationButton(
    modifier: Modifier,
    @DrawableRes drawableIdDef: Int,
    @DrawableRes drawableIdActive: Int,
    name: String,
    currentPage: MutableState<String>,
    nav: NavHostController,
    isExpended: MutableState<Boolean>,
    isAnimationToolBarFinished: MutableState<Boolean>
) {

    Button(
        onClick = {
            if (currentPage.value != name)
                nav.navigate(name) {
                    nav.clearBackStack("home")
                    this.popUpTo("home")
                    if (isExpended.value)
                        isAnimationToolBarFinished.value = false
                    isExpended.value = false

                }

        },
        modifier.padding(6.dp),
        true,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            contentColor = Color.Transparent
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            disabledElevation = 0.dp,
            pressedElevation = 0.dp,
        ),

        ) {
        Box(
            modifier
                .fillMaxSize().padding(10.dp)
                .shadow(
                    if (currentPage.value == name)
                        5.dp
                    else
                        0.dp, clip = true, shape = CircleShape)) {
            Image(
                painter =
                if (currentPage.value == name)
                    painterResource(drawableIdActive)
                else
                    painterResource(drawableIdDef),
                contentDescription = "",
                Modifier
                    .fillMaxSize()

            )
        }

//        Column(modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally) {
//            Image(painter = painterResource(drawableId),
//                    contentDescription ="",
//                    Modifier
//                            .size(25.dp),
//                    colorFilter =
//                    if (currentPage.value == pageName)
//                        ColorFilter.tint(MaterialTheme.colors.secondary)
//                    else
//                        ColorFilter.tint(Color(0x998C8C8C))
//            )
//            if (currentPage.value == pageName)
//            Text(
//                    text = name,
//                    textAlign = TextAlign.Center,
//                    color = MaterialTheme.colors.secondary,
//                    fontSize = MaterialTheme.typography.subtitle1.fontSize
//            )
//        }


    }

}