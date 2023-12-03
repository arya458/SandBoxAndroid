package com.arya.danesh.myresume

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arya.danesh.myresume.pages.AiChatPage
import com.arya.danesh.myresume.pages.AppsPage
import com.arya.danesh.myresume.pages.BlogPage
import com.arya.danesh.myresume.pages.HomePage
import com.arya.danesh.myresume.pages.SkillsPage

//import com.arya.danesh.myresume.ui.theme.surface



fun NavGraphBuilder.homeGraph(isCollapseListener: (Boolean,Boolean) -> Unit) {

    navigation(startDestination = "Home", route = "Main",
        enterTransition = {

            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                animationSpec = tween(durationMillis = 400, delayMillis = 200)

            )

        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                animationSpec = tween(durationMillis = 200, delayMillis = 20)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                animationSpec = tween(durationMillis = 400, delayMillis = 200)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                animationSpec = tween(durationMillis = 200, delayMillis = 20)
            )
        }
    ) {
        composable("News") { BlogPage(isCollapseListener=isCollapseListener) }
        composable("Skills") { SkillsPage(isCollapseListener=isCollapseListener) }
        composable("Home") { HomePage(isCollapseListener=isCollapseListener) }
        composable("Apps") { AppsPage(isCollapseListener=isCollapseListener) }
        composable("ChatBot") { AiChatPage(isCollapseListener=isCollapseListener) }
    }
}


sealed class MainNavigation(val route: String, val image: Int) {
    object Blog : MainNavigation("News", R.drawable.news)
    object Skills : MainNavigation("Skills", R.drawable.idea)
    object Home : MainNavigation("Home", R.drawable.home)
    object AboutUs : MainNavigation("Apps", R.drawable.apps)
    object ContactUs : MainNavigation("ChatBot", R.drawable.bot)
}