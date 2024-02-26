package com.arya.danesh.myresume.data.remote.api

import com.arya.danesh.myresume.data.response.BlogResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {



    @GET("blogs.json")
    suspend fun getBlog():Response<BlogResponse>
    //https://aryajsonbucket.4everland.store/blogs.json
}