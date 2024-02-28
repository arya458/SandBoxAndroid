package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.remote.api.ApiService
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import retrofit2.Response
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
        private val apiService: ApiService
) :PostDataSource {
    override suspend fun getPost(postKey: String): Response<PostResponse> {
       return apiService.getPost(postKey)
    }
}