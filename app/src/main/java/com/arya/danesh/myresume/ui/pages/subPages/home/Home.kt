package com.arya.danesh.myresume.ui.pages.subPages.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.ui.controller.SplashNavigation
import com.arya.danesh.myresume.ui.pages.subPages.home.compose.Guide
import com.arya.danesh.myresume.ui.pages.subPages.home.compose.Project
import com.arya.danesh.myresume.ui.pages.subPages.skills.compose.SkillSmall
import com.arya.danesh.myresume.ui.theme.elv_1

//import com.arya.danesh.myresume.ui.theme.section
//import com.arya.danesh.myresume.ui.theme.title

@Composable
fun HomePage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (SplashNavigation) -> Unit,
) {

    val lazyState = rememberLazyListState()
    val visibleItems = remember { derivedStateOf { lazyState.layoutInfo } }
    val listener by rememberUpdatedState(isCollapseListener)

    listener(lazyState.isScrollInProgress, lazyState.canScrollBackward)





    LazyColumn(
        Modifier
            .fillMaxSize(),
        state = lazyState,
        contentPadding = PaddingValues(top = 20.dp, bottom = 70.dp)
    ) {


        item {
            Guide()

            //Skills
            Surface(
                Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = MaterialTheme.colorScheme.surface,
                elevation = elv_1
            ) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp)
                        .wrapContentHeight()
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                            .wrapContentHeight(), Arrangement.Start, Alignment.CenterVertically
                    )
                    {
                        //todo Set a Good Icon and Text Size and Good Elevation
//                            Image(
//                                modifier = Modifier.size(20.dp),
//                                painter = painterResource(R.drawable.idea_blue),
//                                contentDescription = ""
//                            )

                        Text(
                            "Skills",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onSurface

                        )

                    }

                    LazyRow(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .animateContentSize()
                            .padding(bottom = 5.dp, top = 5.dp),
                        reverseLayout = false,
                    ) {

                        items((1..20).toList()) {

                            SkillSmall()
                        }

                    }

                }

            }


            //Projects
            Surface(
                Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = MaterialTheme.colorScheme.surface,
                elevation = elv_1
            ) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp)
                        .wrapContentHeight()
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                            .wrapContentHeight(), Arrangement.Start, Alignment.CenterVertically
                    )
                    {
                        //todo Set a Good Icon and Text Size and Good Elevation
//                            Image(
//                                modifier = Modifier.size(20.dp),
//                                painter = painterResource(R.drawable.idea_blue),
//                                contentDescription = ""
//                            )

                        Text(
                            "MyProjects",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onSurface,

                            )

                    }

                    LazyRow(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 5.dp, top = 5.dp),
                        reverseLayout = false,

                        ) {
                        items((1..20).toList()) {
                            Project(
                                Modifier
                                    .wrapContentHeight()
                                    .width(300.dp)
                            )
                        }

                    }

                }

            }

            //News
            Surface(
                Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = MaterialTheme.colorScheme.surface,
                elevation = elv_1
            ) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp)
                        .wrapContentHeight()
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                            .wrapContentHeight(), Arrangement.Start, Alignment.CenterVertically
                    )
                    {
                        //todo Set a Good Icon and Text Size and Good Elevation
//                            Image(
//                                modifier = Modifier.size(20.dp),
//                                painter = painterResource(R.drawable.idea_blue),
//                                contentDescription = ""
//                            )

                        Text(
                            "Blog",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onSurface,

                            )

                    }

                    LazyRow(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 5.dp),
                        reverseLayout = false,

                        ) {
//                        items((1..20).toList()) {
//                            Blog(
//                                modifier = Modifier
//                                    .wrapContentHeight()
//                                    .width(340.dp)
//                            )
//                        }

                    }

                }

            }
        }


    }


}