package com.arya.danesh.myresume.di.module

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arya.danesh.myresume.ui.core.state.AppState
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {
    @Provides
    @Singleton
    fun provideAppState() : MutableState<AppState> = mutableStateOf(AppState.Normal)
    @Provides
    @Singleton
    fun provideToolBarState() : MutableState<ToolBarAnimationState> = mutableStateOf(ToolBarAnimationState.EXPENDED)
    @Provides
    @Singleton
    fun provideCurrentPage() : MutableState<String> = mutableStateOf("Home")

    @Provides
    @Singleton
    fun provideMenuState() : MutableState<MenuState> = mutableStateOf(MenuState.COLLAPSE)


    @Provides
    @Singleton
    fun provideIsDark() : Boolean = false


}