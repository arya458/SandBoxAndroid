package com.arya.danesh.myresume.di.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.myresume.repository.ApiRepository
import com.arya.danesh.utilities.CoreUtility.key
import com.arya.danesh.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
        private val apiRepository: ApiRepository,

        ) :ViewModel() {

    private val _post :MutableStateFlow<ResourceState<PostResponse>> = MutableStateFlow(ResourceState.Loading())
    var post :StateFlow<ResourceState<PostResponse>> = _post

    init {
        getPost()
    }

    private fun getPost(){
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getPost()
                    .collectLatest { posts->
                        _post.value = posts
                        Log.d("getPost", "getPost: "+_post.value.toString())
                        Log.d("getPost", "getPostLive: "+"post_"+key+".json")
                    }
        }

    }
    fun tryAgain(){
        _post.tryEmit(ResourceState.Loading())
        post = _post
        Log.d("getPost", "try again Clicked ")
        getPost()
    }
}