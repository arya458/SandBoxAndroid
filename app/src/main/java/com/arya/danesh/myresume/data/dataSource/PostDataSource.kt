package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

interface PostDataSource {

    suspend fun  getPost(postKey: String): Response<PostResponse>




}