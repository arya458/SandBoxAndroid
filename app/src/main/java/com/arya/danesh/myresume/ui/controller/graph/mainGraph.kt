package com.arya.danesh.myresume.ui.controller.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arya.danesh.myresume.ui.controller.route.MainNavigation
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.pages.main.sub.apps.AppsPage
import com.arya.danesh.myresume.ui.pages.main.sub.blog.BlogPage
import com.arya.danesh.myresume.ui.pages.main.sub.home.HomePage
import com.arya.danesh.myresume.ui.pages.main.sub.skills.SkillsPage

fun NavGraphBuilder.mainGraph(navigateTo: (RootNavigation) -> Unit, isCollapseListener: (Boolean, Boolean) -> Unit) {



    navigation(startDestination = MainNavigation.Main.Home.route, route = MainNavigation.Main.route,
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
        composable(MainNavigation.Main.Blog.route) { BlogPage(isCollapseListener=isCollapseListener,navigateTo) }
        composable(MainNavigation.Main.Skills.route) { SkillsPage(isCollapseListener=isCollapseListener,navigateTo) }
        composable(MainNavigation.Main.Home.route) { HomePage(isCollapseListener=isCollapseListener,navigateTo) }
        composable(MainNavigation.Main.Apps.route) { AppsPage(isCollapseListener=isCollapseListener,navigateTo) }
//        composable("ChatBot") { AiChatPage(isCollapseListener=isCollapseListener,navigateTo) }
    }

}
