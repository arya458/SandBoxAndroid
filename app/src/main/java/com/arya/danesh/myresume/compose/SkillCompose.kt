package com.arya.danesh.myresume.compose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun SkillSmall() {

    Card(
        Modifier
            .padding(start = 10.dp, top = 10.dp)
            .size(140.dp, 180.dp),
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = elv_3,
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(Modifier.fillMaxSize(), Arrangement.Top) {

            Image(
                painterResource(R.drawable.kotlin),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp, 140.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .padding(bottom = 10.dp)
                    .shadow(3.dp, RoundedCornerShape(15.dp), clip = true)
            )
            Text(
                "SkillName",
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,

            )


        }


    }

}

@Composable
fun SkillBig(size:Int, isShowing: (Int) -> ComposeItemAnimationState) {


    val transition = updateTransition(isShowing(size), label = "ToolBar State")


    val progressbarAnimation by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 50,
                easing = FastOutSlowInEasing
            )
        }, label = "color"


    ) { state ->

        when (state) {
            ComposeItemAnimationState.HIDDEN -> 0.0f
            ComposeItemAnimationState.VISIBLE -> 0.5f
            else -> {
                0.0f
            }
        }


    }



    Card(
        Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = elv_3,
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .wrapContentHeight(), Arrangement.Top
        ) {

            Row {
                Image(
                    painterResource(R.drawable.kotlin),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp, 140.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .padding(bottom = 10.dp)
                        .shadow(3.dp, RoundedCornerShape(15.dp), clip = true)
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
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,

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
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.6f),

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
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                LinearProgressIndicator(
                    progress = progressbarAnimation,
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                        .shadow(3.dp, RoundedCornerShape(15.dp), clip = true),
                    color = MaterialTheme.colorScheme.primary,
                    strokeCap = StrokeCap.Round,
//                    backgroundColor = progressBarEmpty.copy(0.5f)

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
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                LinearProgressIndicator(
                    progress = progressbarAnimation,
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                        .shadow(3.dp, RoundedCornerShape(15.dp), clip = true),
                    color = MaterialTheme.colorScheme.primary,
                    strokeCap = StrokeCap.Round,
//                    backgroundColor = progressBarEmpty.copy(0.5f)

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
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                LinearProgressIndicator(
                    progress = progressbarAnimation,
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                        .shadow(3.dp, RoundedCornerShape(15.dp), clip = true),
                    color = MaterialTheme.colorScheme.primary,
                    strokeCap = StrokeCap.Round,
//                    backgroundColor = progressBarEmpty.copy(0.5f)

                )
            }

        }

    }
}

