package com.arya.danesh.myresume.pages.sections.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.theme.item
import com.arya.danesh.myresume.ui.theme.progressBarEmpty
import com.arya.danesh.myresume.ui.theme.progressBarFill
import com.arya.danesh.myresume.ui.theme.text
import com.arya.danesh.myresume.ui.theme.title


@Composable
fun SkillSmall(){

    Card(
        Modifier
            .padding(start = 10.dp, top = 10.dp)
            .size(140.dp, 180.dp),
        backgroundColor = item,
        elevation = 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(Modifier.fillMaxSize(),Arrangement.Top) {

            Image(
                painterResource(R.drawable.kotlin),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp, 140.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .padding(bottom = 10.dp)
                    .shadow(5.dp, RoundedCornerShape(15.dp), clip = true)
                )
            Text(
                "SkillName",
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                color = title

            )


        }



    }

}

@Preview()
@Composable
fun SkillBig(){
    Card(
        Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
        ,
        backgroundColor = item,
        elevation = 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .wrapContentHeight(),Arrangement.Top) {

            Row() {
                Image(
                    painterResource(R.drawable.kotlin),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp, 140.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .padding(bottom = 10.dp)
                        .shadow(5.dp, RoundedCornerShape(15.dp), clip = true)
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp), Arrangement.Top
                ) {
                    //todo add text size
                    Text(
                        "SkillName",
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .wrapContentHeight(),
                        textAlign = TextAlign.Start,
                        color = title

                    )
                    Text(
                        text = "To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .wrapContentHeight(),
                        softWrap = true,
                        maxLines = 5,
                        minLines = 3,
                        overflow  = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        color = text,
                    )
                }
            }



            Column(
                Modifier.fillMaxWidth(), Arrangement.Top
            ) {
                Text(
                    text = "Kotlin :",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    color = title,
                )
                LinearProgressIndicator(
                    progress = 0.5f,
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                        .shadow(5.dp, RoundedCornerShape(15.dp), clip = true),
                    color = progressBarFill,
                    strokeCap = StrokeCap.Round,
                    backgroundColor = progressBarEmpty.copy(0.5f)

                )
            }
            Column(
                Modifier.fillMaxWidth(), Arrangement.Top
            ) {
                Text(
                    text = "Kotlin :",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    color = title,
                )
                LinearProgressIndicator(
                    progress = 0.5f,
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                        .shadow(5.dp, RoundedCornerShape(15.dp), clip = true),
                    color = progressBarFill,
                    strokeCap = StrokeCap.Round,
                    backgroundColor = progressBarEmpty.copy(0.5f)

                )
            }
            Column(
                Modifier.fillMaxWidth(), Arrangement.Top
            ) {
                Text(
                    text = "Kotlin :",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    color = title,
                )
                LinearProgressIndicator(
                    progress = 0.5f,
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                        .shadow(5.dp, RoundedCornerShape(15.dp), clip = true),
                    color = progressBarFill,
                    strokeCap = StrokeCap.Round,
                    backgroundColor = progressBarEmpty.copy(0.5f)

                )
            }

        }

    }
}

