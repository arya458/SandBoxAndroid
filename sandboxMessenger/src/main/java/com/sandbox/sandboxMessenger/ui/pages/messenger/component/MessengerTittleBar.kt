package com.sandbox.sandboxMessenger.ui.pages.messenger.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.Buttons.NavigationButton
import com.arya.danesh.utilities.state.NavButtonAnimationState
import com.arya.danesh.coreui.theme.elv_3
import com.sandbox.sandboxmessenger.R


@Composable
fun MessengerTittleBar(username:String,imageBitmap: ImageBitmap?,isOnline:Boolean,onBackClick:()->Unit,userOnClick:()->Unit){

    Surface(Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            , color = MaterialTheme.colorScheme.surface, shadowElevation = elv_3){

        Row(Modifier

                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 10.dp,top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding(),  bottom = 10.dp, start = 10.dp)
                ,Arrangement.SpaceBetween,Alignment.CenterVertically){
            
            //BackButton
            NavigationButton(
                    modifier = Modifier
                            .size(50.dp)
                            .padding(0.dp),
                    drawable = R.drawable.baseline_arrow_back_ios_new_24,
                    contentDescription = "Back",
                    buttonState = NavButtonAnimationState.ACTIVE
            ) {
                onBackClick()
            }
            
            Column(Modifier.wrapContentSize(),Arrangement.Center,Alignment.CenterHorizontally) {
                Text(
                        text = username,
                        modifier = Modifier
                                .wrapContentSize(),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color =MaterialTheme.colorScheme.onSurface,
                )
//            if (ToolBarAnimationState.EXPENDED == toolBarState)
                Text(
                        text = if (isOnline) "Online" else "Offline",
                        modifier = Modifier
                                .wrapContentSize(),
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                )
            }

            Surface(Modifier.size(45.dp), color = Color.Transparent) {

                if (imageBitmap != null) {
                    Image(
                            bitmap = imageBitmap,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier

                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .shadow(elv_3, CircleShape, clip = true)
                                    .clickable { userOnClick() },
                    )
                }

////        Column(Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.End) {
//            Surface(Modifier.layout { measurable, constraints ->
//                val placeable = measurable.measure(constraints.copy(constraints.minWidth/6,constraints.minWidth/6,constraints.minHeight/6,constraints.minHeight/6))
//                layout(10,10){
//                    placeable.place(constraints.minWidth/3,constraints.maxHeight/3)
//                }
//
//            }.size(14.dp), color = if (isOnline) Color.Green else Color.Gray, shape = CircleShape, shadowElevation = elv_3) {}
////        }


            }
            
            
        }

    }

}