package com.arya.danesh.myresume.ui.pages.main.compose

import android.os.Build
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.controller.route.RootNavigation
import com.arya.danesh.myresume.data.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun SideMenu(navigateTo: (RootNavigation) -> Unit, sharedData: SharedViewModel = hiltViewModel(), stiffness: Float) {

    val insets = WindowInsetsCompat.Type.systemGestures()

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp
    val screenWidthHalf = (configuration.screenWidthDp.dp/3)*2-20.dp

    val transition = updateTransition(sharedData.getmenuState(), label = "ToolBar State")
    val menuScale by transition.animateFloat(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            MenuState.EXPENDED -> 1f
            MenuState.COLLAPSE -> 0f
        }

    }
    val menuTranslation by transition.animateDp(
            transitionSpec = {
                spring(
                        stiffness = stiffness,
                        dampingRatio = 0.46f,
                )
            }, label = "color"

    ) { state ->

        when (state) {
            MenuState.EXPENDED -> 0.dp
            MenuState.COLLAPSE -> screenWidth*-1
        }

    }


    //Menu
    Row(
            Modifier
                    .graphicsLayer {
                        alpha = menuScale
                        translationX = menuTranslation.toPx()
                    }
                    .fillMaxSize(),
            Arrangement.Start,
    ) {

        Column(
                Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = insets.dp * 2, bottom = insets.dp * 2)
                        .weight(0.65f)
//                    .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colorScheme.surface),


                )
        {

//            Surface(Modifier
//                    .size(screenWidthHalf),
//                    elevation = elv_3,
//                    shape = RoundedCornerShape(15.dp,15.dp,0.dp,0.dp,),
//                    color = MaterialTheme.colorScheme.primaryContainer
//            ) {
//
//
////            Image(painter = painterResource(R.drawable.def),
////                    contentDescription = "",
////                    Modifier.fillMaxSize(),
////                    contentScale = ContentScale.FillBounds
////
////            )
//            }

//            Text(
//                    text = "MatrixUserName",
//                    modifier = Modifier.fillMaxWidth().wrapContentSize().padding(start = 5.dp, top = 10.dp),
//                    color = if (Build.VERSION.SDK_INT <= 31) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
//                    style = MaterialTheme.typography.titleMedium,
//                    textAlign = TextAlign.Center,
//            )


        }
        Spacer(modifier = Modifier
                .fillMaxSize()
                .weight(0.35f))


    }

}