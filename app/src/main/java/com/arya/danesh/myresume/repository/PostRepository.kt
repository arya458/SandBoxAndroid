package com.arya.danesh.myresume.repository

import com.arya.danesh.myresume.data.dataSource.BlogDataSource
import com.arya.danesh.myresume.data.dataSource.PostDataSource
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(
        private val postDataSource: PostDataSource
) {

//    suspend fun getBlog():Response<BlogResponse>{
//        return blogDataSource.getBlog()
//    }

    suspend fun getPost(postKey: String): Flow<ResourceState<PostResponse>> {

        return flow {
            emit(ResourceState.Loading())
            val response = postDataSource.getPost(postKey)

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