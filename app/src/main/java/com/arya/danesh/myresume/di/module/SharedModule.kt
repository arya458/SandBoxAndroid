package com.arya.danesh.myresume.di.module

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arya.danesh.utilities.state.AppState
import com.arya.danesh.utilities.state.MenuState
import com.arya.danesh.utilities.state.ToolBarAnimationState
import com.arya.danesh.utilities.ResourceState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
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

//    @Provides
//    @Singleton
//    @Name
//    fun provideBlogCurrentPost() : MutableStateFlow<String> =  MutableStateFlow("")

    @Provides
    @Singleton
    fun provideIsDark() : Boolean = false


}