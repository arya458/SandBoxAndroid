package com.arya.danesh.myresume.data.remote.api

import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("apps.json")
    suspend fun getApp():Response<AppResponse>

    @GET("blogs.json")
    suspend fun getBlog():Response<BlogResponse>

    @GET("posts/{postKey}")
    suspend fun getPost(
            @Path(value = "postKey", encoded = true)
            postKey: String
    ):Response<PostResponse>
}