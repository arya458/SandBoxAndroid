package com.arya.danesh.myresume.ui.controller.graph

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.pages.main.Main
import com.arya.danesh.myresume.ui.pages.profile.ProfilePage
import com.arya.danesh.myresume.ui.pages.readBlog.ReadBlog
import com.arya.danesh.myresume.ui.pages.splash.SplashPage
import com.sandbox.sandboxMessenger.ui.pages.authorization.LoginPage
import com.sandbox.sandboxMessenger.ui.pages.authorization.RegisterPage
import com.sandbox.sandboxMessenger.ui.pages.loading.LoadingPage
import com.sandbox.sandboxMessenger.ui.pages.messenger.MessengerPage

fun NavGraphBuilder.rootGraph(navigateTo: (RootNavigation) -> Unit) {


    navigation(startDestination = RootNavigation.Root.Loading.route, route = RootNavigation.Root.route,
            enterTransition = {

                fadeIn(
                        animationSpec = tween(durationMillis = 400, delayMillis = 200)

                )

            },
            exitTransition = {
                fadeOut(
                        animationSpec = tween(durationMillis = 200, delayMillis = 20)
                )
            },
            popEnterTransition = {
                fadeIn(
                        animationSpec = tween(durationMillis = 400, delayMillis = 200)
                )
            },
            popExitTransition = {
                fadeOut(
                        animationSpec = tween(durationMillis = 200, delayMillis = 20)
                )
            }
    ) {

        composable(RootNavigation.Root.Loading.route) { LoadingPage(navigateTo) }
        composable(RootNavigation.Root.Splash.route) { SplashPage(navigateTo) }
        composable(RootNavigation.Root.Login.route) { LoginPage(navigateTo) }
        composable(RootNavigation.Root.Register.route) { RegisterPage(navigateTo) }
//        navigation("Home","MainPage", builder = {introGraph(navigateTo,appState,setAppState) })


        composable(RootNavigation.Root.MainPage.route) { Main(navigateTo = navigateTo) }

        composable(RootNavigation.Root.ReadBlog.route) { ReadBlog(navigateTo) }
        composable(RootNavigation.Root.ProfilePage.route) { ProfilePage(navigateTo) }
        composable(RootNavigation.Root.Messenger.route) { MessengerPage(navigateTo) }
    }

}