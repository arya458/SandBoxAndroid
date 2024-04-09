package com.arya.danesh.myresume.di.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arya.danesh.myresume.data.response.SkillResponse
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
class SkillViewModel @Inject constructor(
        private val apiRepository: ApiRepository,

        ) :ViewModel() {

    private val _skill :MutableStateFlow<ResourceState<SkillResponse>> = MutableStateFlow(ResourceState.Loading())
    var skill :StateFlow<ResourceState<SkillResponse>> = _skill


    init {
        getSkills()
    }

    private fun getSkills(){
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getSkills()
                    .collectLatest { skills->
                        _skill.value = skills
                        Log.d("getPost", "getBlog: "+_skill.value.toString())
                    }
        }
    }

    fun tryAgain(){
        _skill.tryEmit(ResourceState.Loading())
        skill = _skill
        Log.d("getPost", "try again Clicked ")
        getSkills()
    }


}