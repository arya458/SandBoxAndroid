package com.arya.danesh.myresume.pages.sections.toolbar

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.theme.text
import com.arya.danesh.myresume.ui.theme.title


@Composable
fun ToolBar(
    state: ScrollState,
    currentPage: MutableState<String>,
    isExpended: MutableState<Boolean>,
    isAnimationToolBarFinished: MutableState<Boolean>
) {




    val rowSize by animateDpAsState(
        targetValue = if (isExpended.value) 260.dp else 45.dp,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (isExpended.value) 200 else 50,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    val imageSize by animateDpAsState(
        targetValue = if (isExpended.value) 100.dp else 30.dp,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (isExpended.value) 50 else 200,
            easing = FastOutSlowInEasing
        ), label = "",
        finishedListener = {
            isAnimationToolBarFinished.value = true
        }
    )
    val imageBlur by animateDpAsState(
        targetValue = if (isAnimationToolBarFinished.value) 0.dp else 50.dp,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 0,
            easing = FastOutSlowInEasing
        ), label = "",)

    val columnAlignment by animateFloatAsState(
        targetValue = if (!isExpended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (isExpended.value) 50 else 200,
            easing = FastOutSlowInEasing
        ),
        label = "",


        )

    val textVisibilityExpended by animateFloatAsState(
        targetValue = if (!isExpended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (isExpended.value) 50 else 200,
            easing = FastOutSlowInEasing
        ),
        label = "",


        )
    val textVisibilityCollapsed by animateFloatAsState(
        targetValue = if (isExpended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = if (isExpended.value) 50 else 200,
            easing = FastOutSlowInEasing
        ),
        label = "",


        )




    Row(

        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .height(rowSize),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isExpended.value) Text(
            text = currentPage.value, modifier = Modifier.alpha(textVisibilityExpended),
            color = title
        )
        Column(
            horizontalAlignment = BiasAlignment.Horizontal(columnAlignment),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp)
        ) {
            //todo Remove onclick and set scroll handler
            Image(
                painter = painterResource(R.drawable.kotlin),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(imageSize, imageSize)
                    .clip(CircleShape)
                    .shadow(5.dp, CircleShape, clip = true)
                    .blur(imageBlur)
                    .clickable { isExpended.value = !isExpended.value
                               Log.d("tester", state.value.toString())
                            isAnimationToolBarFinished.value = false
                               },
            )

            if (isExpended.value) Text(
                text = "Arya", modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp).alpha(textVisibilityCollapsed),
                color = title
            )
            if (isExpended.value) Text(
                text = "TestTestTest", modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp).alpha(textVisibilityCollapsed),
                color = text
            )


        }
    }


//        AnimatedContent(
//            targetState = isExpended.value,
//            transitionSpec = {
//                fadeIn(initialAlpha = 0f,animationSpec = tween(delayTime, delayTime)) togetherWith
//                        fadeOut(targetAlpha = 1f,animationSpec = tween(delayTime, delayTime)) using
//                        SizeTransform() { initialSize, targetSize ->
//                            if (targetState) {
//                                keyframes {
//                                    // Expand horizontally first.
//                                    durationMillis = delayTime+100
//                                    IntSize(targetSize.width, initialSize.height) at delayTime
////                                    rowModifier.height(targetSize.height.dp)
//
//                                }
//                            } else {
//                                keyframes {
//                                    // Shrink vertically first.
////                                    rowModifier.height(initialSize.height.dp)
//                                    IntSize(targetSize.width, 45) at delayTime
//                                    durationMillis = delayTime+100
//                                }
//                            }
//                        }
//            }, label = ""
//        ) { targetExpanded ->
//            Row(
//
//                Modifier
//                    .fillMaxWidth()
//                    .padding(start = 10.dp, end = 10.dp)
//                    .height(if (targetExpanded) 260.dp else 45.dp)
//                ,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//            if (!targetExpanded) Text(text = currentPage.value, modifier = Modifier,
//                color = title)
//            Column(
//                horizontalAlignment = if (targetExpanded) Alignment.CenterHorizontally else Alignment.End,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(5.dp)
//            ) {
//                //todo Remove onclick and set scroll handler
//                Image(
//                    painter = painterResource(R.drawable.ic_launcher_background),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .clip(CircleShape)
//                        .shadow(5.dp,CircleShape ,clip = true)
//                        .clickable { isExpended.value = !targetExpanded },
//                    )
//
//                if (targetExpanded) Text(text = "Arya", modifier =  Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 0.dp),
//                    color = title
//                )
//                if (targetExpanded) Text(text = "TestTestTest", modifier =  Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
//                    color = text)
//
//            }
//        }
//
//
//    }

}