package com.arya.danesh.myresume.data.module

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arya.danesh.myresume.ui.core.state.AppState
import com.arya.danesh.myresume.ui.core.state.MenuState
import com.arya.danesh.myresume.ui.core.state.ToolBarAnimationState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {
    @Provides
    fun provideAppState() : MutableState<AppState> = mutableStateOf(AppState.Normal)
    @Provides
    fun provideToolBarState() : MutableState<ToolBarAnimationState> = mutableStateOf(ToolBarAnimationState.EXPENDED)
    @Provides
    fun provideCurrentPage() : MutableState<String> = mutableStateOf("Home")

    @Provides
    fun provideMenuState() : MutableState<MenuState> = mutableStateOf(MenuState.COLLAPSE)
}