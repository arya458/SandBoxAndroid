package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.remote.api.ApiService
import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import retrofit2.Response
import javax.inject.Inject

class AppDataSourceImpl @Inject constructor(
        private val apiService: ApiService
) :AppDataSource {
    override suspend fun getApp(): Response<AppResponse> {
       return apiService.getApp()
    }
}