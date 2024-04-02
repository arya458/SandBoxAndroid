package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.myresume.data.response.SkillResponse
import retrofit2.Response

interface ApiDataSource {


    suspend fun getBlog(): Response<BlogResponse>
    suspend fun  getPost(postKey: String): Response<PostResponse>
    suspend fun  getSkill(): Response<SkillResponse>
    suspend fun  getApp(): Response<AppResponse>




}