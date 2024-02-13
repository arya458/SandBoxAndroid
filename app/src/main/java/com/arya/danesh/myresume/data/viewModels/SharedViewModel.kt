package com.arya.danesh.myresume.data.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arya.danesh.myresume.ui.core.state.AppState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState

class SharedViewModel : ViewModel() {
    val appState = mutableStateOf(AppState.Normal)
    val toolBarState = mutableStateOf(ToolBarAnimationState.EXPENDED)
    val currentPage = mutableStateOf("Home")
}

