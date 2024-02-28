package com.arya.danesh.myresume.ui.core.component.Texts

import android.os.Build
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.di.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState

@Composable
fun AlphaAnimText(
        modifier: Modifier,
        text:String,
        isViewOnCollapse :Boolean,
        color : Color = if (Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
        style : TextStyle = MaterialTheme.typography.titleMedium,
        textAlign : TextAlign = TextAlign.Justify,
        sharedData: SharedViewModel = hiltViewModel()
        ) {




    val transition = updateTransition(sharedData.getToolBarState(), label = "ToolBar State")

    val textVisibilityTitle by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = if (sharedData.getToolBarState() == ToolBarAnimationState.COLLAPSE) 400f else 200f,
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
            ToolBarAnimationState.EXPENDED -> if (isViewOnCollapse) 0f else 1f
            ToolBarAnimationState.COLLAPSE ->  if (isViewOnCollapse) 1f else 0f
        }


    }


    Text(
            text = text,
            modifier = modifier
                    .alpha(textVisibilityTitle)
                    .scale(textVisibilityTitle, textVisibilityTitle),
            color = color,
            style = style,
            textAlign = textAlign,
    )

}