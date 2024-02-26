package com.arya.danesh.myresume.repository

import com.arya.danesh.myresume.data.dataSource.BlogDataSource
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class BlogRepository @Inject constructor(
        private val blogDataSource: BlogDataSource
) {

//    suspend fun getBlog():Response<BlogResponse>{
//        return blogDataSource.getBlog()
//    }

    suspend fun getBlog(): Flow<ResourceState<BlogResponse>> {

        return flow {
            emit(ResourceState.Loading())
            val response = blogDataSource.getBlog()

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