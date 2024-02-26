package com.arya.danesh.myresume.di.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.repository.BlogRepository
import com.arya.danesh.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

import javax.inject.Inject


@HiltViewModel
class BlogViewModel @Inject constructor(
        private val blogRepository: BlogRepository

) :ViewModel() {

    private val _blog :MutableStateFlow<ResourceState<BlogResponse>> = MutableStateFlow(ResourceState.Loading())
    val blog :StateFlow<ResourceState<BlogResponse>> = _blog


    init {
        getBlog()
    }

    private fun getBlog(){
        viewModelScope.launch(Dispatchers.IO) {
            blogRepository.getBlog()
                    .collectLatest { blogResponse->

                        _blog.value = blogResponse

                    }
        }
    }

}