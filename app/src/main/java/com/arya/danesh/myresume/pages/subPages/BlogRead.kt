package com.arya.danesh.myresume.pages.subPages

import android.graphics.BlurMaskFilter.Blur
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.SplashNavigation
import com.arya.danesh.myresume.compose.AnimText
import com.arya.danesh.myresume.state.ComposeItemAnimationState
import com.arya.danesh.myresume.state.ReadState
import kotlin.math.log


@Composable
fun ReadBlog(navigateTo: (SplashNavigation) -> Unit) {

    val configuration = LocalConfiguration.current
    val scrollState = rememberScrollState()
    val imageScrollState = rememberScrollState()
    val readState = remember { mutableStateOf(ReadState.EXPENDED) }
    //onLaunched Anime
    val isLaunched = remember { mutableStateOf(ComposeItemAnimationState.HIDDEN) }
    LaunchedEffect(Unit) {
        isLaunched.value = ComposeItemAnimationState.VISIBLE
//        snapshotFlow { readState.value }.collect { v ->
//            Log.d("tester", "ReadBlog: ${v.name}")
//            if (v == ReadState.FULL_IMAGE)
//                imageScrollState.animateScrollTo(imageScrollState.maxValue)
//            else
//                imageScrollState.animateScrollTo(imageScrollState.maxValue/2)
//        }

    }




    val alphaAnim = 1f
    val rawText =
            "To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use To make an image fit into a shape, use the built-in clip modifier. To crop an image into a circle shape, use".repeat(
                    40
            )




    if (scrollState.isScrollInProgress)
        if (scrollState.canScrollBackward) {

                readState.value = ReadState.COLLAPSED
        } else {
            readState.value = ReadState.EXPENDED
        }

    val transition = updateTransition(readState, label = "ReadAnim State")
    val sizeAnim = transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = 200f,
                        dampingRatio = 0.42f,
                )
            }, label = "color"


    ) { state ->

        when (state.value) {
            ReadState.COLLAPSED -> 100.dp
            ReadState.EXPENDED -> 400.dp
            ReadState.FULL_IMAGE -> configuration.screenHeightDp.dp
        }


    }
    val blurAnim = transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = 200f,
                        dampingRatio = 0.42f,
                )
            }, label = "color"


    ) { state ->

        when (state.value) {
            ReadState.COLLAPSED -> 30.dp
            ReadState.EXPENDED -> 0.dp
            ReadState.FULL_IMAGE -> 0.dp
        }


    }
    val brightness = transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = 200f,
                        dampingRatio = 0.42f,
                )
            }, label = "color"


    ) { state ->

        when (state.value) {
            ReadState.COLLAPSED -> if (isSystemInDarkTheme()) -30f else 30f
            ReadState.EXPENDED -> 0f
            ReadState.FULL_IMAGE -> 0f
        }


    }

    //Image Size And Painter
    val imagePainter = painterResource(R.drawable.def)
    val imageWidth = (imagePainter.intrinsicSize.width * sizeAnim.value) / imagePainter.intrinsicSize.height
    val colorMatrix = floatArrayOf(
            1f, 0f, 0f, 0f, brightness.value,
            0f, 1f, 0f, 0f, brightness.value,
            0f, 0f, 1f, 0f, brightness.value,
            0f, 0f, 0f, 1f, 0f
    )

    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        Column(Modifier
                .fillMaxSize()
                .animateContentSize()) {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(sizeAnim.value)
                    .horizontalScroll(imageScrollState, readState.value == ReadState.FULL_IMAGE)
                    .blur(blurAnim.value)
                    .clickable {
                        if (readState.value == ReadState.FULL_IMAGE)
                            readState.value = ReadState.EXPENDED
                        else
                            readState.value = ReadState.FULL_IMAGE

                    }, Arrangement.Start) {
                Image(
                        imagePainter,
                        contentDescription = "",
                        contentScale = if (readState.value == ReadState.FULL_IMAGE) ContentScale.Fit else ContentScale.Crop,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                        modifier = Modifier
                                .width(if (readState.value == ReadState.FULL_IMAGE) imageWidth else configuration.screenHeightDp.dp)
                                .defaultMinSize(configuration.screenWidthDp.dp)
                                .fillMaxHeight()


                )
            }
            Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight())
        }
        Column(Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
            Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(sizeAnim.value - 20.dp))
            Column(Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = MaterialTheme.colorScheme.surface, RoundedCornerShape(15.dp))
                    .padding(10.dp),
                    Arrangement.Top,
                    Alignment.CenterHorizontally) {
                Text(
                        text = "Post title",
                        modifier = Modifier
                                .alpha(alphaAnim)
                                .fillMaxWidth()
                                .padding(10.dp)
                                .wrapContentHeight(),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                )
                AnimText(
                        rawText = rawText,
                        animState = isLaunched.value,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .wrapContentHeight(),
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                        durationMillis = 2000,
                        delayMillis = 500
                )

            }

        }


    }


}

