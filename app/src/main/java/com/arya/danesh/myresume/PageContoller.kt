package com.arya.danesh.myresume

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arya.danesh.myresume.pages.Main
import com.arya.danesh.myresume.pages.subPages.AiChatPage
import com.arya.danesh.myresume.pages.subPages.AppsPage
import com.arya.danesh.myresume.pages.subPages.BlogPage
import com.arya.danesh.myresume.pages.subPages.HomePage
import com.arya.danesh.myresume.pages.subPages.LoadingPage
import com.arya.danesh.myresume.pages.subPages.SkillsPage
import com.arya.danesh.myresume.pages.subPages.SplashPage




fun NavGraphBuilder.homeGraph(isCollapseListener: (Boolean, Boolean) -> Unit) {



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

        //Main
        composable("News") { BlogPage(isCollapseListener=isCollapseListener) }
        composable("Skills") { SkillsPage(isCollapseListener=isCollapseListener) }
        composable("Home") { HomePage(isCollapseListener=isCollapseListener) }
        composable("Apps") { AppsPage(isCollapseListener=isCollapseListener) }
        composable("ChatBot") { AiChatPage(isCollapseListener=isCollapseListener) }
    }

}
//Splash
fun NavGraphBuilder.intoGraph(navigateTo: (SplashNavigation) -> Unit) {



    navigation(startDestination = "Loading", route = "Into",
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
        composable("Loading") { LoadingPage(navigateTo) }
        composable("Splash") { SplashPage(navigateTo) }
        composable("MainPages") { Main()}
    }

}


sealed class MainNavigation(val route: String, val image: Int) {
    object Blog : MainNavigation("News", R.drawable.news)
    object Skills : MainNavigation("Skills", R.drawable.idea)
    object Home : MainNavigation("Home", R.drawable.home)
    object AboutUs : MainNavigation("Apps", R.drawable.apps)
    object ContactUs : MainNavigation("ChatBot", R.drawable.bot)
}

sealed class SplashNavigation(val route: String){
    object Splash : SplashNavigation("Splash")
    object Loading : SplashNavigation("Loading")
    object Main : SplashNavigation("MainPages")
}
