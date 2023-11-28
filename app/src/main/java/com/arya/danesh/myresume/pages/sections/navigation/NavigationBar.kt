package com.arya.danesh.myresume.pages.sections.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arya.danesh.myresume.MainNavigation
import kotlinx.coroutines.delay
import kotlin.concurrent.timerTask
import kotlin.time.Duration

//import com.arya.danesh.myresume.ui.theme.navbarDark
//import com.arya.danesh.myresume.ui.theme.navbarLight


val items = listOf(
    MainNavigation.Blog,
    MainNavigation.Skills,
    MainNavigation.Home,
    MainNavigation.AboutUs,
    MainNavigation.ContactUs,
)

@Composable
fun NavigationBar(
    navHostController: NavHostController,
    currentpage: MutableState<String>,
    isExpended: MutableState<Boolean>,
    isAnimationToolBarFinished: MutableState<Boolean>
) {


    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination




    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 10.dp, end = 10.dp, start = 10.dp),
//            .background(brush = Brush.verticalGradient(colors = listOf(navbarLight,navbarDark)))
        shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
        elevation = 3.dp,
    ) {


        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
//        NavigationButton(
//            modifier = Modifier.size(60.dp),
//            drawableIdActive = R.drawable.blog_blue,
//            drawableIdDef = R.drawable.blog_gray,
//            name = "blog",
//            currentPage = currentpage,
//            nav = navHostController,
//            isExpended=isExpended,
//            isAnimationToolBarFinished = isAnimationToolBarFinished
//        )
            items.forEach { mainItem ->

                NavigationButton(
                    modifier = Modifier.size(60.dp),
                    drawable =
                    if (currentDestination?.hierarchy?.any { it.route == mainItem.route } == true)
                        mainItem.pressedImage
                    else
                        mainItem.defImage
                ) {
                    if (isAnimationToolBarFinished.value)
                        if (currentDestination?.hierarchy?.any { it.route == mainItem.route } == false) {
                            navHostController.navigate(mainItem.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                currentpage.value = mainItem.route

                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = false

                                }

                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true

//                                if (isExpended.value)
//                                    isAnimationToolBarFinished.value = false
//                                isExpended.value = false
                            }

                        }
                }


            }
        }

    }


}