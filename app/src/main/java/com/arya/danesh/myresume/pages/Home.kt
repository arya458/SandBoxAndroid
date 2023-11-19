package com.arya.danesh.myresume.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.pages.sections.views.Blog
import com.arya.danesh.myresume.pages.sections.views.Project
import com.arya.danesh.myresume.pages.sections.views.SkillSmall
import com.arya.danesh.myresume.ui.theme.section
import com.arya.danesh.myresume.ui.theme.title

@Composable
fun home(name: String, currentPage: MutableState<String>, modifier: Modifier = Modifier) {
    currentPage.value = name
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        contentPadding = PaddingValues(top = 20.dp , bottom = 20.dp)
    ) {

        item {

            //Skills
            Surface(
                Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = section,
                elevation = 5.dp
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
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.idea_blue),
                            contentDescription = ""
                        )

                        Text(
                            "Skills",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            color = title

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
                color = section,
                elevation = 5.dp
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
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.idea_blue),
                            contentDescription = ""
                        )

                        Text(
                            "MyProjects",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            color = title

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
                            Project(Modifier.wrapContentHeight().width(300.dp))
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
                color = section,
                elevation = 5.dp
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
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.idea_blue),
                            contentDescription = ""
                        )

                        Text(
                            "Blog",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            color = title

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
                            Blog(modifier = Modifier.wrapContentHeight().width(340.dp))
                        }

                    }

                }

            }
        }

    }

}