package com.arya.danesh.myresume.pages.sections.Toolbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.theme.text
import com.arya.danesh.myresume.ui.theme.title


@Composable
fun ToolBar(
    state: ScrollState,
    currentPage: MutableState<String>,
    isExpended: MutableState<Boolean>
) {




    val delayTime = 300

        AnimatedContent(
            targetState = isExpended.value,
            transitionSpec = {
                fadeIn(initialAlpha = 0f,animationSpec = tween(delayTime, delayTime)) togetherWith
                        fadeOut(targetAlpha = 1f,animationSpec = tween(delayTime, delayTime)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, targetSize.height) at delayTime
                                    durationMillis = delayTime+100
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.

                                    IntSize(targetSize.width, targetSize.height) at delayTime
                                    durationMillis = delayTime+100
                                }
                            }
                        }
            }, label = ""
        ) { targetExpanded ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .height(if (targetExpanded) 260.dp else 45.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
            if (!targetExpanded) Text(text = currentPage.value, modifier = Modifier,
                color = title)
            Column(
                horizontalAlignment = if (targetExpanded) Alignment.CenterHorizontally else Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                //todo Remove onclick and set scroll handler
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(CircleShape)
                        .shadow(5.dp,CircleShape ,clip = true)
                        .clickable { isExpended.value = !targetExpanded },
                    )

                if (targetExpanded) Text(text = "Arya", modifier =  Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp),
                    color = title
                )
                if (targetExpanded) Text(text = "TestTestTest", modifier =  Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
                    color = text)

            }
        }
//        AnimatedVisibility(
//            visible = !isOpen.value,
//            enter = fadeIn(
//                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
//                initialAlpha = alpha,
//                animationSpec = tween(durationMillis = delayTime)
//            ),
//            exit = fadeOut(
//                // Overwrites the default animation with tween
//                targetAlpha = alpha,
//                animationSpec = tween(durationMillis = delayTime)
//            )
//        ) {
//            Text(text = "PageTitle", modifier = titleModifier)
//        }
//        Column(
//            horizontalAlignment = if (isOpen.value) Alignment.CenterHorizontally else Alignment.End,
//            modifier = columnModifier
//        ) {
//            Image(
//                painter = painterResource(R.drawable.ic_launcher_background),
//                contentDescription = "",
//                modifier = imageModifier,
//
//                )
//            AnimatedVisibility(
//                visible = isOpen.value,
//                enter = fadeIn(
//                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
//                    initialAlpha = alpha,
//                    animationSpec = tween(durationMillis = delayTime)
//
//                ),
//                exit = fadeOut(
//                    // Overwrites the default animation with tween
//                    targetAlpha = alpha,
//                    animationSpec = tween(durationMillis = delayTime)
//                )
//            ) {
//                Text(text = "Arya", modifier = nameModifier)
//                Text(text = "TestTestTest", modifier = textModifier)
//            }
//        }

    }

}