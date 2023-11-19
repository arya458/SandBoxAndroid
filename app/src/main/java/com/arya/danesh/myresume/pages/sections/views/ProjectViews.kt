package com.arya.danesh.myresume.pages.sections.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.theme.appLight
import com.arya.danesh.myresume.ui.theme.item
import com.arya.danesh.myresume.ui.theme.text
import com.arya.danesh.myresume.ui.theme.title


@Composable
fun Project(modifier: Modifier) {

    Card(
        modifier
            .padding(10.dp),
        backgroundColor = item,
        elevation = 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {


        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .wrapContentHeight(),
            Arrangement.Top
        ) {
            Image(
                painterResource(R.drawable.kotlin),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(15.dp))
                    .padding(bottom = 10.dp)
                    .shadow(5.dp, RoundedCornerShape(15.dp), clip = true)
            )
            //todo add text size
            Text(
                "SkillName",
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, bottom = 5.dp, top = 10.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Start,
                color = title

            )

            Text(
                text = "To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use".repeat(
                    10
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 5.dp, top = 5.dp)
                    .wrapContentHeight(),
                softWrap = true,
                maxLines = 7,
                minLines = 5,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = text,
            )

            LazyRow(Modifier.fillMaxWidth().wrapContentHeight().padding(bottom = 5.dp, top = 5.dp), reverseLayout = true) {
                items((1..5).toList()) {
                    Card(Modifier
                        .wrapContentSize()
                        .padding(2.dp),
                        backgroundColor = item,
                        border = BorderStroke(1.dp, appLight),
                        shape = RoundedCornerShape(15.dp),
                        elevation = 2.dp

                    ) {
                        Text(
                            text = "Kotlin",
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(bottom = 5.dp, top = 5.dp, start = 10.dp, end = 10.dp)
                            ,
                            textAlign = TextAlign.Center,
                            style = LocalTextStyle.current.merge(
                                TextStyle(
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = false
                                    ),
                                    lineHeightStyle = LineHeightStyle(
                                        alignment = LineHeightStyle.Alignment.Center,
                                        trim = LineHeightStyle.Trim.None
                                    )
                                )
                            ),
                            color = appLight
                        )
                    }
                }
            }

        }


    }

}