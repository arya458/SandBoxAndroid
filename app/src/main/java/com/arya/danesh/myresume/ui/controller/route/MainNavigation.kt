package com.arya.danesh.myresume.ui.controller.route

import com.arya.danesh.myresume.R

sealed class MainNavigation(val route: String,val image: Int=0) {

    object Main : MainNavigation("Main") {
        object Blog : MainNavigation("News", R.drawable.news)
        object Skills : MainNavigation("Skills", R.drawable.idea)
        object Home : MainNavigation("Home", R.drawable.home)
        object Apps : MainNavigation("Apps", R.drawable.apps)
        object Messenger : MainNavigation("Messenger", R.drawable.bot)
    }
}