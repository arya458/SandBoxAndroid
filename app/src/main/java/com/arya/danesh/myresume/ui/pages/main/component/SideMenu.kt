package com.arya.danesh.myresume.ui.pages.main.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.controller.route.RootNavigation
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun SideMenu(navigateTo: (RootNavigation) -> Unit, sharedData: SharedViewModel = hiltViewModel<SharedViewModel>(), stiffness: Float) {


    val scrollState = rememberLazyListState()


    Column(
            Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = WindowInsets.systemBars
                                    .asPaddingValues()
                                    .calculateTopPadding(),
                            bottom = WindowInsets.navigationBars
                                    .asPaddingValues()
                                    .calculateBottomPadding()

                    )

//                    .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surface),


            )
    {

        Column(Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp, top = 20.dp)
                .height(140.dp),
                Arrangement.SpaceEvenly,
                Alignment.CenterHorizontally
        ) {

            Image(painter = painterResource(R.drawable.def),
                    contentDescription = "",
                    Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .shadow(elv_3, clip = true),
                    contentScale = ContentScale.FillBounds
            )

            Column(Modifier.wrapContentSize(), Arrangement.SpaceBetween, Alignment.Start) {

                Text(
                        text = "MatrixUserName",
                        modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 10.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                )
                Text(
                        text = "user:Matrix.org",
                        modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 5.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                )

            }


        }
//            Spacer(modifier = Modifier
//                    .padding(top = 10.dp, bottom = 10.dp)
//                    .fillMaxWidth(0.5f)
//                    .height(1.dp)
//                    .clip(RoundedCornerShape(15.dp))
//                    .shadow(elv_3, clip = true)
//                    .background(MaterialTheme.colorScheme.outlineVariant)
//                    .align(Alignment.CenterHorizontally))


        LazyColumn(Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(elv_3)),
                contentPadding = PaddingValues(start = 5.dp, end = 5.dp, top = 5.dp),
                state = scrollState

        ) {
            item() {
                MenuItemTitle()

            }
            items(5) {
                MenuIconTextButton() {

                    if (sharedData.getIsDark())
                        sharedData.setIsDark(false)
                    else
                        sharedData.setIsDark(true)
                }
            }
            item() {
                MenuItemTitle()

            }
            items(5) {
                MenuIconTextButton() {}
            }

        }

//            Spacer(modifier = Modifier
//                    .padding(top = 10.dp, bottom = 10.dp)
//                    .fillMaxWidth(0.5f)
//                    .height(1.dp)
//                    .clip(RoundedCornerShape(15.dp))
//                    .shadow(elv_3, clip = true)
//                    .background(MaterialTheme.colorScheme.outlineVariant)
//                    .align(Alignment.CenterHorizontally))


        Column(Modifier
                .fillMaxWidth()
                .height(50.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
        ) {
//                MenuItemTitle()
//                Row(Modifier.wrapContentSize(),Arrangement.Center,Alignment.CenterVertically) {
//
//                    Surface(Modifier.size(50.dp)
//                            .clickable {
//
//                                       MaterialTheme.colorScheme.
//                            },
//                            shape = RoundedCornerShape(15.dp),
//                            color = Color.Green,
//                            elevation = elv_3) {
//
//                    }
//                    Surface(Modifier.size(50.dp)
//                            .clickable {
//
//
//                            },
//                            shape = RoundedCornerShape(15.dp),
//                            color = MaterialTheme.colorScheme.primary,
//                            elevation = elv_3) {
//
//                    }
//                    Surface(Modifier.size(50.dp)
//                            .clickable {
//
//
//                            },
//                            shape = RoundedCornerShape(15.dp),
//                            color = Color.Magenta,
//                            elevation = elv_3) {
//
//                    }

//                }
            Text(
                    text = "Version " + sharedData.getAppVersion(),
                    modifier = Modifier
                            .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
            )

        }


    }


}

@Composable
fun MenuIconTextButton(onclick: () -> Unit) {
    Row(Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(15.dp))
            .clickable { onclick }, Arrangement.Start, Alignment.CenterVertically) {

        Image(
                painter = painterResource(R.drawable.home),
                contentDescription = "NaviButton",
                Modifier
                        .size(45.dp)
                        .padding(14.dp)
                        .background(Color.Transparent),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outline),
                contentScale = ContentScale.Inside

        )
        Text(
                text = "DarkMode",
                modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp),
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
        )

    }
}

@Composable
fun MenuItemTitle() {
    Column(Modifier
            .fillMaxWidth()
            .wrapContentHeight(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(
                text = "Menu",
                modifier = Modifier
                        .padding(top = 10.dp)
                        .wrapContentSize(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(0.8f)
                .height(1.dp)
                .clip(RoundedCornerShape(15.dp))
                .shadow(elv_3, clip = true)
                .background(MaterialTheme.colorScheme.primary)
                .align(Alignment.CenterHorizontally))
    }
}