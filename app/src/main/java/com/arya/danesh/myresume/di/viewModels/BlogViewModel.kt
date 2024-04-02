package com.arya.danesh.myresume.di.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arya.danesh.myresume.data.response.BlogResponse
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
class BlogViewModel @Inject constructor(
        private val apiRepository: ApiRepository,

        ) :ViewModel() {

    private val _blog :MutableStateFlow<ResourceState<BlogResponse>> = MutableStateFlow(ResourceState.Loading())
    val blog :StateFlow<ResourceState<BlogResponse>> = _blog


    init {
        getBlog()
    }

    private fun getBlog(){
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getBlog()
                    .collectLatest { blogs->
                        _blog.value = blogs
                        Log.d("ErrorRoot", "getBlog: "+_blog.value.toString())
                    }
        }
    }

    fun selectPost(postKey:String){
        apiRepository.selectBlogPost(postKey)
    }


}