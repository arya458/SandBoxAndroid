package com.arya.danesh.myresume.compose.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.arya.danesh.myresume.MainNavigation
import com.arya.danesh.myresume.ui.theme.elv_2

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
    currentDestination: NavDestination?,
    onClick: (MainNavigation) -> Unit
) {


    Row(Modifier.wrapContentHeight().fillMaxWidth(),Arrangement.Center,Alignment.CenterVertically) {


        Card(
            Modifier
                .requiredWidthIn(310.dp, 360.dp)
                .wrapContentHeight()
                .padding(bottom = 10.dp, end = 10.dp, start = 10.dp),
//            .background(brush = Brush.verticalGradient(colors = listOf(navbarLight,navbarDark)))
            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
            elevation = elv_2,
        ) {


            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                items.forEach { mainItem ->

                    NavigationButton(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(2.dp),
                        drawable = mainItem.image,
                        color =
                        if (currentDestination?.hierarchy?.any { it.route == mainItem.route } == true)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline
                    ) {
                        onClick(mainItem)
                    }


                }
            }

        }
    }


}

