package com.arya.danesh.myresume.pages.sections.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
//import com.arya.danesh.myresume.ui.theme.appLight
//import com.arya.danesh.myresume.ui.theme.item
//import com.arya.danesh.myresume.ui.theme.text
//import com.arya.danesh.myresume.ui.theme.title


@Composable
fun Blog(modifier: Modifier) {
    Card(
        modifier
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {


        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp), Arrangement.Top
        ) {
            //todo add text size
            Text(
                "PostTitle",
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface,

            )
            Image(
                painterResource(R.drawable.kotlin),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(15.dp))
                    .padding(bottom = 10.dp)
                    .shadow(3.dp, RoundedCornerShape(15.dp), clip = true)
            )
            Text(
                text = "To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use".repeat(
                    10
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .wrapContentHeight(),
                softWrap = true,
                maxLines = 7,
                minLines = 5,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colors.onSurface.copy(0.6f),
            )
            Text(
                text = "ReadMore",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                    .wrapContentHeight(),
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
                color = MaterialTheme.colors.primary,
            )
        }


    }
}