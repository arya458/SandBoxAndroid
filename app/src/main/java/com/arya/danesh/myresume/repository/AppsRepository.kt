package com.arya.danesh.myresume.repository

import com.arya.danesh.myresume.data.dataSource.AppDataSource
import com.arya.danesh.myresume.data.dataSource.BlogDataSource
import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AppsRepository @Inject constructor(
        private val appDataSource: AppDataSource
) {

//    suspend fun getBlog():Response<BlogResponse>{
//        return blogDataSource.getBlog()
//    }

    suspend fun getApps(): Flow<ResourceState<AppResponse>> {

        return flow {
            emit(ResourceState.Loading())
            val response = appDataSource.getApp()

            if (response.isSuccessful && response.body() != null) {

                emit(ResourceState.Success(response.body()!!))

            } else {
                emit(ResourceState.Error("Error :" + response.code().toString()))
            }

        }
                .catch {
                    emit(ResourceState.Error("Flow Error : " + it.localizedMessage))
                }
    }

}