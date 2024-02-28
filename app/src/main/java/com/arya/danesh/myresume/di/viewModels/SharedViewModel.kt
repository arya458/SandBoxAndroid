package com.arya.danesh.myresume.di.viewModels

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.ui.core.state.AppState
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
        private val appState: MutableState<AppState>,
        private val toolBarState: MutableState<ToolBarAnimationState>,
        private val currentPage: MutableState<String>,
        private val menuState : MutableState<MenuState>,
        private val isDark : Boolean,
        private val applicationContext: Application,

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
    fun getmenuState() : MenuState {
        return menuState.value
    }
    fun setmenuState(it:MenuState){
        menuState.value=it
    }

    fun getIsDark() : Boolean {
        return isDark
    }

    fun setIsDark(isDark: Boolean) {
//        val context = getAppContext()
//        val sharedPref = context
//                .getSharedPreferences(context.getString(R.string.preference_setting_key), Context.MODE_PRIVATE)
//        sharedPref.edit().putBoolean(context.getString(R.string.preference_setting_isDark_key), isDark).apply()

    }

    fun getAppContext() : Application {
        return applicationContext
    }

    fun getAppVersion() : String {
        return applicationContext.packageManager.getPackageInfo(applicationContext.packageName,0).versionName
    }

}

