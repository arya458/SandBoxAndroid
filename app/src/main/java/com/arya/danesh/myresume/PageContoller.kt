package com.arya.danesh.myresume

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import androidx.core.widget.NestedScrollView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arya.danesh.myresume.pages.aboutUs
import com.arya.danesh.myresume.pages.blog
import com.arya.danesh.myresume.pages.contactUs
import com.arya.danesh.myresume.pages.home
import com.arya.danesh.myresume.pages.skills
import com.arya.danesh.myresume.ui.theme.surface

@Composable
fun pageController(navController: NavHostController, currentPage: MutableState<String>) {



    Surface(Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
        .shadow(5.dp, clip = true), color = surface) {
        NavHost(navController = navController, startDestination = "home") {
            composable("blog") { blog("blog", currentPage = currentPage) }
            composable("skills") { skills("skills", currentPage = currentPage) }
            composable("home") { home("home", currentPage = currentPage) }
            composable("aboutUs") { aboutUs("aboutUs", currentPage = currentPage) }
            composable("contactUs") { contactUs("contactUs", currentPage = currentPage) }
            /*...*/
        }
    }



}