package com.arya.danesh.myresume.data.dataSource

import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

interface AppDataSource {

    suspend fun  getApp(): Response<AppResponse>




}