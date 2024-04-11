package com.arya.danesh.myresume.ui.pages.main.sub.skills.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arya.danesh.coreui.texts.AnimText
import com.arya.danesh.coreui.texts.TextSubTittle
import com.arya.danesh.coreui.theme.elv_3
import com.arya.danesh.myresume.data.response.Skills
import com.arya.danesh.utilities.state.ComposeItemAnimationState


@Composable
fun SkillsCompose(onclick: () -> Unit,
                  size: Int,skills: Skills,isShowing: (Int) -> ComposeItemAnimationState){

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
        Row(Modifier.fillMaxSize().padding(5.dp)) {

            AsyncImage(
                    model = skills.logo,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .alpha(alphaAnim)
                            .size(100.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .shadow(3.dp, RoundedCornerShape(15.dp), clip = true)
            )

            Column(Modifier.fillMaxSize().padding(start = 5.dp)) {
                TextSubTittle(
                        text = skills.tittle,
                        modifier = Modifier
                                .alpha(alphaAnim)
                                .fillMaxWidth()
                                .padding(bottom = 5.dp)
                                .wrapContentHeight(),
//                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface,
                )
                AnimText(
                        rawText = skills.text,
                        setAnimState = {it.value = isShowing(size)},
                        modifier = Modifier
                                .fillMaxWidth()
                                .alpha(alphaAnim)
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
            }

        }
    }

}



