package com.arya.danesh.myresume.ui.pages.main.sub.blog.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import coil.compose.AsyncImage
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.data.response.BlogPost
import com.arya.danesh.myresume.ui.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.core.component.AnimText
import com.arya.danesh.myresume.ui.core.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun BlogCompose(
        onclick: () -> Unit,
        size: Int,
        post: BlogPost,
        isShowing: (Int) -> ComposeItemAnimationState
) {

    val transition = updateTransition(isShowing(size), label = "Blog State")

    val alphaAnim by transition.animateFloat(
        transitionSpec = {
//            spring(
//                stiffness = 10000f,
//                dampingRatio = 0.36f,
//            )
            tween(
                durationMillis = 1000,
                delayMillis = 50,
//                easing = FastOutSlowInEasing
            )
        }, label = "color"


    ) { state ->

        when (state) {
            ComposeItemAnimationState.HIDDEN -> 0f
            ComposeItemAnimationState.VISIBLE -> 1f
        }


    }
//
//    val textAnim = rawText.substring(startIndex = 0, endIndex = textMaxAnim)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .padding(10.dp),
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = elv_3,
        shape = RoundedCornerShape(15.dp)
    ) {


        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp), Arrangement.Top
        ) {
            Text(
                text = post.tittle,
                modifier = Modifier
                    .alpha(alphaAnim)
                    .fillMaxWidth()
                    .padding(10.dp)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )
            AsyncImage(
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .alpha(alphaAnim)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(15.dp))
                    .padding(bottom = 10.dp)
                    .shadow(3.dp, RoundedCornerShape(15.dp), clip = true)
            )
            AnimText(
                rawText = post.detail,
                setAnimState = {it.value = isShowing(size)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .wrapContentHeight(),
                softWrap = true,
                maxLines = 7,
                minLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                durationMillis = 2000,
                delayMillis = 200
            )
            Text(
                text = "ReadMore",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                    .wrapContentHeight()
                    .clickable{onclick()},
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
                color = MaterialTheme.colorScheme.primary,
            )
        }


    }
}