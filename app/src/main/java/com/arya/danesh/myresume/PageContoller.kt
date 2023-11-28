package com.arya.danesh.myresume

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arya.danesh.myresume.pages.AboutUsPage
import com.arya.danesh.myresume.pages.BlogPage
import com.arya.danesh.myresume.pages.HomePage
import com.arya.danesh.myresume.pages.SkillsPage
import com.arya.danesh.myresume.pages.ContactUsPage
import kotlinx.coroutines.delay

//import com.arya.danesh.myresume.ui.theme.surface

@Composable
fun PageController(
    navController: NavHostController,
    lazyState: LazyListState,
    isExpended: MutableState<Boolean>,
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
        composable("aboutUs") { AboutUsPage(lazyState = lazyState) }
        composable("contactUs") { ContactUsPage(lazyState = lazyState) }
    }
}


sealed class MainNavigation(val route: String, val pressedImage: Int, val defImage: Int) {
    object Blog : MainNavigation("blog", R.drawable.blog_blue, R.drawable.blog_gray)
    object Skills : MainNavigation("skills", R.drawable.idea_blue, R.drawable.idea_gray)
    object Home : MainNavigation("home", R.drawable.home_blue, R.drawable.home_gray)
    object AboutUs : MainNavigation("aboutUs", R.drawable.newinfo_blue, R.drawable.newinfo_gray)
    object ContactUs :
        MainNavigation("contactUs", R.drawable.newmessage_blue, R.drawable.newmessage_gray)
}