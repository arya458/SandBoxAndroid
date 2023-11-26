package com.arya.danesh.myresume.pages.sections.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.theme.navbarDark
import com.arya.danesh.myresume.ui.theme.navbarLight


@Composable
fun NavigationBar(
    navHostController: NavHostController,
    currentpage: MutableState<String>,
    isExpended: MutableState<Boolean>,
    isAnimationToolBarFinished: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(20.dp)
            .background(brush = Brush.verticalGradient(colors = listOf(navbarLight,navbarDark)))
    )
    {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
//            .background(colors.surface)
            Arrangement.SpaceEvenly,
            Alignment.CenterVertically,

            ) {

            NavigationButton(
                modifier = Modifier.size(65.dp),
                drawableIdActive = R.drawable.blog_blue,
                drawableIdDef = R.drawable.blog_gray,
                name = "blog",
                currentPage = currentpage,
                nav = navHostController,
                isExpended=isExpended,
                isAnimationToolBarFinished = isAnimationToolBarFinished
            )
            NavigationButton(
                modifier = Modifier.size(65.dp),
                drawableIdActive = R.drawable.idea_blue,
                drawableIdDef = R.drawable.idea_gray,
                name = "skills",
                currentPage = currentpage,
                nav = navHostController,
                isExpended=isExpended,
                isAnimationToolBarFinished = isAnimationToolBarFinished
            )
            NavigationButton(
                modifier = Modifier.size(65.dp),
                drawableIdActive = R.drawable.home_blue,
                drawableIdDef = R.drawable.home_gray,
                name = "home",
                currentPage = currentpage,
                nav = navHostController,
                isExpended=isExpended,
                isAnimationToolBarFinished = isAnimationToolBarFinished
            )
            NavigationButton(
                modifier = Modifier.size(65.dp),
                drawableIdActive = R.drawable.newinfo_blue,
                drawableIdDef = R.drawable.newinfo_gray,
                name = "aboutUs",
                currentPage = currentpage,
                nav = navHostController,
                isExpended=isExpended,
                isAnimationToolBarFinished = isAnimationToolBarFinished
            )
            NavigationButton(
                modifier = Modifier.size(65.dp),
                drawableIdActive = R.drawable.newmessage_blue,
                drawableIdDef = R.drawable.newmessage_gray,
                name = "contactUs",
                currentPage = currentpage,
                nav = navHostController,
                isExpended=isExpended,
                isAnimationToolBarFinished = isAnimationToolBarFinished
            )

        }
    }

}