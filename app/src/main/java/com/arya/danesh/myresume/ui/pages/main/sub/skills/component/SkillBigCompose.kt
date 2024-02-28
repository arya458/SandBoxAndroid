package com.arya.danesh.myresume.ui.pages.main.sub.skills.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.core.component.Texts.AnimText
import com.arya.danesh.myresume.ui.core.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun SkillBigCompose(size: Int, isShowing: (Int) -> ComposeItemAnimationState) {


//    val alphaAnim = remember { Animatable(0.0f) }
//    val progressbarAnimation = remember { Animatable(0.0f) }
//    LaunchedEffect(Unit) {
//            alphaAnim.animateTo(1f, animationSpec = spring(400f,0.36f))
//            progressbarAnimation.animateTo(0.5f, animationSpec = spring(400f,0.36f))
//    }
//
    val transition = updateTransition(isShowing(size), label = "ToolBar State")

    val rawText = "To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use"

//    val textMaxAnim by transition.animateInt(
//        transitionSpec = {
////            spring(
////                stiffness = 10000f,
////                dampingRatio = 0.36f,
////            )
//            tween(
//                durationMillis = 4000,
//                delayMillis =500,
////                easing = FastOutSlowInEasing
//            )
//        }, label = "color"
//
//
//    ) { state ->
//
//        when (state) {
//            ComposeItemAnimationState.HIDDEN ->0
//            ComposeItemAnimationState.VISIBLE -> rawText.length
//        }
//
//
//    }
//
//    val textAnim = rawText.substring(startIndex = 0, endIndex = textMaxAnim)

    val alphaAnim by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = 200f,
                        dampingRatio = 0.36f,
                )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
//                easing = FastOutSlowInEasing
//            )
            }, label = "color"


    ) { state ->

        when (state) {
            ComposeItemAnimationState.HIDDEN -> 0f
            ComposeItemAnimationState.VISIBLE -> 1f
        }


    }
    val progressbarAnimation by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = 200f,
                        dampingRatio = 0.36f,
                )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
//                easing = FastOutSlowInEasing
//            )
            }, label = "color"


    ) { state ->

        when (state) {
            ComposeItemAnimationState.HIDDEN -> 0.0f
            ComposeItemAnimationState.VISIBLE -> 0.7f
        }


    }



    Card(
            Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .animateContentSize()
                    .alpha(alphaAnim)
                    .padding(10.dp)
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
                            text = "Skill Title",
                            modifier = Modifier
                                    .alpha(alphaAnim)
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .wrapContentHeight(),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                    )
                    AnimText(
                            rawText = rawText,
                            setAnimState = { it.value = isShowing(size) },
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
                            durationMillis = 2000,
                            delayMillis = 200
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

