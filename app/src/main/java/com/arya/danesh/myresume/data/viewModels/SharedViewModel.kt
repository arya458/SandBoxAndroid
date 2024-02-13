package com.arya.danesh.myresume.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arya.danesh.myresume.ui.core.state.AppState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
        private val appState: MutableState<AppState>,
        private val toolBarState: MutableState<ToolBarAnimationState>,
        private val currentPage: MutableState<String>

) : ViewModel() {
    fun getAppState() : AppState {
       return appState.value
    }
    fun setAppState(it: AppState){
        appState.value = it
    }
    fun getToolBarState() : ToolBarAnimationState {
        return toolBarState.value
    }
    fun setToolBarState(it:ToolBarAnimationState){
        toolBarState.value = it
    }
    fun getCurrentPage() : String {
        return currentPage.value
    }
    fun setCurrentPage(it:String){
        currentPage.value=it
    }
}

