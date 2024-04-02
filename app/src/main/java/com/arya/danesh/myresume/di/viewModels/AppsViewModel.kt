package com.arya.danesh.myresume.di.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.repository.ApiRepository
import com.arya.danesh.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class AppsViewModel @Inject constructor(
        private val apiRepository: ApiRepository,

        ) :ViewModel() {

    private val _apps :MutableStateFlow<ResourceState<AppResponse>> = MutableStateFlow(ResourceState.Loading())
    val apps :StateFlow<ResourceState<AppResponse>> = _apps


    init {
        getApps()
    }

    private fun getApps(){
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getApps()
                    .collectLatest { apps->
                        _apps.value = apps
                        Log.d("getPost", "getBlog: "+_apps.value.toString())
                    }
        }
    }


}