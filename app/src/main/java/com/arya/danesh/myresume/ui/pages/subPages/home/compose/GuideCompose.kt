package com.arya.danesh.myresume.ui.pages.subPages.home.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.theme.elv_3


@Preview
@Composable
fun Guide() {

    Surface (
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 10.dp, start = 10.dp,end = 10.dp),
        color = Color.Transparent

    ) {

        Card(
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = elv_3,
            border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
            backgroundColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 110.dp)
                    .wrapContentHeight(),Arrangement.Top,Alignment.Start) {

                Text(
                    "Welcome !",
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,

                    )
                Text(
                    text = "Hope You Like My Resume ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .wrapContentHeight(),
                    softWrap = true,
                    maxLines = 5,
                    minLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                    style = MaterialTheme.typography.labelMedium,

                    )

            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 10.dp)
                .wrapContentHeight(),Arrangement.Bottom,Alignment.End) {
            Surface(Modifier.wrapContentSize(), color = Color.Transparent) {


                Image(
                    painter = painterResource(R.drawable.intro),
                    contentDescription = "",
                    Modifier.size(122.dp).background(Color.Transparent),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                )
                Image(
                    painter = painterResource(R.drawable.intro),
                    contentDescription = "",
                    Modifier.size(120.dp).background(Color.Transparent)

                )
            }
        }
    }

}