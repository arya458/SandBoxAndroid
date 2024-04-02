package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.remote.api.ApiService
import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.myresume.data.response.SkillResponse
import retrofit2.Response
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
        private val apiService: ApiService
) :ApiDataSource {
    override suspend fun getBlog(): Response<BlogResponse> {
        return apiService.getBlog()
    }

    override suspend fun getPost(postKey: String): Response<PostResponse> {
        return apiService.getPost(postKey)
    }

    override suspend fun getSkill(): Response<SkillResponse> {
        return apiService.getSkill()
    }

    override suspend fun getApp(): Response<AppResponse> {
       return apiService.getApp()
    }
}