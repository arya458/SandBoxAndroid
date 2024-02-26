package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.remote.api.ApiService
import com.arya.danesh.myresume.data.response.BlogResponse
import retrofit2.Response
import javax.inject.Inject

class BlogDataSourceImpl @Inject constructor(
        private val apiService: ApiService
) :BlogDataSource {
    override suspend fun getBlog(): Response<BlogResponse> {
       return apiService.getBlog()
    }
}