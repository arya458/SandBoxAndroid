package com.arya.danesh.controller.route

sealed class RootNavigation(val route: String) {
    object Root: RootNavigation("Root") {
        object Splash : RootNavigation("Splash")
        object Loading : RootNavigation("Loading")
        object Login : RootNavigation("Login")
        object Register : RootNavigation("Register")
        object MainPage : RootNavigation("Main")

        object ReadBlog : RootNavigation("ReadBlogPage")
        object Messenger : RootNavigation("MessengerPage")
        object ProfilePage : RootNavigation("ProfilePage")
    }

}