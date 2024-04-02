package com.arya.danesh.myresume.ui.core.component.customToolbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.R
import com.sandbox.sandboxMessenger.di.viewModels.MessengerViewModel
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.utilities.state.ToolBarAnimationState

@Composable
fun CustomToolBar(
//        modifier: Modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight(),
        sharedData: SharedViewModel = hiltViewModel(),
        apiViewModel: MessengerViewModel = hiltViewModel(),
        stiffness: Float,
        menuOnclick: () -> Unit,

        ) {


//    val configuration = LocalConfiguration.current
//
//    val screenHeight = 300.dp.value
//    val screenWidth = configuration.screenWidthDp.dp.value

    val profile by apiViewModel.supportProfile.collectAsState()

    val transition = updateTransition(sharedData.getToolBarState(), label = "ToolBar State")
    val rowSize by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 300.dp
            ToolBarAnimationState.COLLAPSE -> 55.dp
        }

    }

    val nameScale by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.36f,
                )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 400 else 0,
//                easing = FastOutSlowInEasing
//            )
            }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 1f
            ToolBarAnimationState.COLLAPSE -> 0f

        }
    }

    val titleScale by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.36f,
                )
//            tween(
//                durationMillis = 300,
//                delayMillis = if (toolBarState == ToolBarAnimationState.COLLAPSE) 400 else 0,
//                easing = FastOutSlowInEasing
//            )
            }, label = "color"


    ) { state ->

        when (state) {
            ToolBarAnimationState.EXPENDED -> 0f
            ToolBarAnimationState.COLLAPSE -> 1f

        }
    }






    Box(Modifier
            .padding(top = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateTopPadding())
            .fillMaxWidth()
            .height(rowSize)
            .animateContentSize()) {


        Image(
                painter = painterResource(R.drawable.menu),
                contentDescription = "Menu",
                Modifier
                        .graphicsLayer {

                            translationY = 5.dp.toPx()
                            translationX = 5.dp.toPx()

                        }
                        .clip(RoundedCornerShape(15.dp))
                        .size(45.dp)
                        .clickable { menuOnclick() },
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(
                        if (android.os.Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
                )

        )


        ProfileImage(toolbarState = sharedData.getToolBarState()) { }

        Text(
                text = sharedData.getCurrentPage(),
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .graphicsLayer {


                            scaleX = titleScale
                            scaleY = titleScale
                            alpha = titleScale
                            translationY = 25.dp.toPx() - size.height / 2

                        },
                color = if (android.os.Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
        )


        Column(Modifier
                .graphicsLayer {

                    scaleX = nameScale
                    scaleY = nameScale
                    alpha = nameScale

                    translationY = rowSize.toPx() / 2


                }
                .fillMaxWidth()
                .wrapContentHeight(), Arrangement.Center, Alignment.CenterHorizontally)
        {


            Text(
                    text = profile.displayName + "",
                    modifier = Modifier
                            .wrapContentSize(unbounded = true)
                            .padding(top = 20.dp),
                    color = if (android.os.Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
            )
            Text(
                    text = "Java And Kotlin Programmer",
                    modifier = Modifier
                            .wrapContentSize(unbounded = true)
                            .padding(top = 5.dp),
                    color = if (android.os.Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(15.dp))
            GroupButtons(modifier = Modifier
                    .padding(5.dp)
                    .size(40.dp), imagePadding = 10.dp)


        }


    }


}


