package com.arya.danesh.myresume.ui.pages.main.sub.apps.component


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arya.danesh.coreui.Texts.AnimText
import com.arya.danesh.coreui.theme.elv_3
import com.arya.danesh.myresume.data.response.Apps
import com.arya.danesh.utilities.state.ComposeItemAnimationState

@Composable
fun AppsCompose(app: Apps, size: Int, isShowing: (Int) -> ComposeItemAnimationState) {

    val transition = updateTransition(isShowing(size), label = "ToolBar State")


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
//    val progressbarAnimation by transition.animateFloat(
//            transitionSpec = {
//                spring(
//                        stiffness = 200f,
//                        dampingRatio = 0.36f,
//                )
////            tween(
////                durationMillis = 300,
////                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 0 else 400,
////                easing = FastOutSlowInEasing
////            )
//            }, label = "color"
//    ) { state ->
//
//        when (state) {
//            ComposeItemAnimationState.HIDDEN -> 0.0f
//            ComposeItemAnimationState.VISIBLE -> targetValueByState
//        }
//
//
//    }



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
                AsyncImage(
                        model = app.logo,
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .padding(bottom = 10.dp)
                                .shadow(3.dp, RoundedCornerShape(15.dp), clip = true)
                                .background(MaterialTheme.colorScheme.surface)
                )
                Column(
                        Modifier
                                .fillMaxWidth()
                                .padding(5.dp), Arrangement.Top
                ) {
                    //todo add text size
                    Text(
                            text = app.name,
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
                            rawText = app.detail,
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
            Spacer(modifier = Modifier.size(10.dp))
                app.skills.forEach {
                    Column(
                            Modifier.fillMaxWidth(), Arrangement.Top
                    ) {
                        Text(
                                text = it.tittle,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                                        .wrapContentHeight(),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                        )
                        LinearProgressIndicator(
                                progress = transition.animateFloat(
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
                                        ComposeItemAnimationState.VISIBLE -> it.percentage/100.0f
                                    }


                                }.value,
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
}