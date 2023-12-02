package com.arya.danesh.myresume

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arya.danesh.myresume.pages.AiChatPage
import com.arya.danesh.myresume.pages.AppsPage
import com.arya.danesh.myresume.pages.BlogPage
import com.arya.danesh.myresume.pages.HomePage
import com.arya.danesh.myresume.pages.SkillsPage

//import com.arya.danesh.myresume.ui.theme.surface

@Composable
fun PageController(
    navController: NavHostController,
    lazyState: LazyListState,
) {


    NavHost(navController = navController, startDestination = "main") {

        homeGraph(lazyState)
    }


}

fun NavGraphBuilder.homeGraph(lazyState: LazyListState) {
    navigation(startDestination = "home", route = "main",
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
        composable("blog") { BlogPage(lazyState = lazyState) }
        composable("skills") { SkillsPage(lazyState = lazyState) }
        composable("home") { HomePage(lazyState = lazyState) }
        composable("apps") { AppsPage(lazyState = lazyState) }
        composable("ai") { AiChatPage(lazyState = lazyState) }
    }
}


sealed class MainNavigation(val route: String, val Image: Int) {
    object Blog : MainNavigation("blog", R.drawable.news)
    object Skills : MainNavigation("skills", R.drawable.idea)
    object Home : MainNavigation("home", R.drawable.home)
    object AboutUs : MainNavigation("apps", R.drawable.apps)
    object ContactUs :
        MainNavigation("ai", R.drawable.bot)
}