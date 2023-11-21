package com.arya.danesh.myresume

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arya.danesh.myresume.pages.AboutUsPage
import com.arya.danesh.myresume.pages.BlogPage
import com.arya.danesh.myresume.pages.HomePage
import com.arya.danesh.myresume.pages.SkillsPage
import com.arya.danesh.myresume.pages.ContactUsPage
import com.arya.danesh.myresume.ui.theme.surface

@Composable
fun PageController(
    navController: NavHostController,
    currentPage: MutableState<String>,
    lazyState: LazyListState,
) {



    Surface(Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
        .shadow(5.dp, clip = true), color = surface) {
        NavHost(navController = navController, startDestination = "home") {
            composable("blog") { BlogPage("blog", currentPage = currentPage,lazyState = lazyState) }
            composable("skills") { SkillsPage("skills", currentPage = currentPage,lazyState = lazyState) }
            composable("home") { HomePage("home", currentPage = currentPage,lazyState = lazyState) }
            composable("aboutUs") { AboutUsPage("aboutUs", currentPage = currentPage,lazyState = lazyState) }
            composable("contactUs") { ContactUsPage("contactUs", currentPage = currentPage,lazyState = lazyState) }
            /*...*/
        }
    }



}