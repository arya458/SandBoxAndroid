package com.arya.danesh.myresume.di.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.arya.danesh.myresume.repository.AppRepository
import com.arya.danesh.utilities.state.AppState
import com.arya.danesh.utilities.state.MenuState
import com.arya.danesh.utilities.state.ToolBarAnimationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
        private val appRepository : AppRepository
        ) : ViewModel() {
    fun getAppState() : AppState {
       return appRepository.getAppState()
    }
    fun setAppState(it: AppState){
        appRepository.setAppState(it)
    }
    fun getToolBarState() : ToolBarAnimationState {
        return appRepository.getToolBarState()
    }
    fun setToolBarState(it: ToolBarAnimationState){
        appRepository.setToolBarState(it)
    }
    fun getCurrentPage() : String {
        return appRepository.getCurrentPage()
    }
    fun setCurrentPage(it:String){
        appRepository.setCurrentPage(it)
    }
    fun getMenuState() : MenuState {
        return appRepository.getMenuState()
    }
    fun setMenuState(it: MenuState){
        appRepository.setMenuState(it)
    }

    fun getIsDark() : Boolean {
        return appRepository.getDarkMode()
    }

    fun setIsDark(isDark: Boolean) {
        appRepository.setDarkMode(isDark)
    }

    fun getAppContext() : Application {
        return appRepository.getContext()
    }

    fun getAppVersion() : String {
        val context = appRepository.getContext()
        return context.packageManager.getPackageInfo(context.packageName,0).versionName
    }

}

