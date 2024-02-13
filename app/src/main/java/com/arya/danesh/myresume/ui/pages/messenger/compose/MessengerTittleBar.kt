package com.arya.danesh.myresume.ui.pages.messenger.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.data.viewModels.SharedViewModel
import com.arya.danesh.myresume.ui.core.compose.customToolbar.ProfileImage
import com.arya.danesh.myresume.ui.core.compose.navigation.NavigationButton
import com.arya.danesh.myresume.ui.core.state.NavButtonAnimationState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import com.arya.danesh.myresume.ui.theme.elv_3


@Composable
fun MessengerTittleBar(username:String,isOnline:Boolean,onBackClick:()->Unit,userOnClick:()->Unit,sharedData: SharedViewModel = hiltViewModel()){

    val insets = WindowInsetsCompat.Type.systemGestures()
    sharedData.setToolBarState(ToolBarAnimationState.COLLAPSE)
    Surface(Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            , color = MaterialTheme.colorScheme.surface, shadowElevation = elv_3){

        Row(Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 10.dp, top = insets.dp*2 +10.dp,  bottom = 10.dp, start = 10.dp)
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

            ProfileImage(){
                userOnClick()
            }
            
            
        }

    }

}