package com.arya.danesh.myresume.repository

import android.app.Application
import com.arya.danesh.utilities.state.AppState
import com.arya.danesh.utilities.state.MenuState
import com.arya.danesh.utilities.state.ToolBarAnimationState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
        private val context : Application
){

    private var appState : AppState = AppState.Normal
    private var toolBarState : ToolBarAnimationState = ToolBarAnimationState.EXPENDED
    private var menuState : MenuState = MenuState.COLLAPSE
    private var currentPage : String = "Home"
    private var isDark : Boolean = false

    fun setAppState(state : AppState){
        appState = state
    }

    fun setToolBarState(state : ToolBarAnimationState){
        toolBarState = state
    }
    fun setMenuState(state : MenuState){
        menuState = state
    }
    fun setCurrentPage(pageName : String){
        currentPage = pageName
    }

    fun setDarkMode(isDark : Boolean){
        this.isDark =isDark
    }

    fun getAppState(): AppState {
        return appState
    }

    fun getToolBarState(): ToolBarAnimationState {
        return toolBarState
    }
    fun getMenuState(): MenuState {
        return menuState
    }
    fun getCurrentPage(): String {
        return currentPage
    }

    fun getDarkMode(): Boolean {
        return isDark
    }

    fun getContext(): Application {
        return context
    }


}