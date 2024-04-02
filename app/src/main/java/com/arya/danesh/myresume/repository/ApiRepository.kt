package com.arya.danesh.myresume.repository

import android.util.Log
import com.arya.danesh.myresume.data.dataSource.ApiDataSource
import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.myresume.data.response.SkillResponse
import com.arya.danesh.utilities.CoreUtility
import com.arya.danesh.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
        private val apiDataSource: ApiDataSource
) {

//    suspend fun getBlog():Response<BlogResponse>{
//        return blogDataSource.getBlog()
//    }
    private var selectedPostKey : String = ""

    suspend fun getBlog(): Flow<ResourceState<BlogResponse>> {

        return flow<ResourceState<BlogResponse>> {
                emit(ResourceState.Loading())
                val response = apiDataSource.getBlog()
                Log.d("ErrorRoot", "response: ${response.isSuccessful}  "+response.code())

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

    fun selectBlogPost(postKey : String){
        selectedPostKey = postKey
    }

    suspend fun getPost(): Flow<ResourceState<PostResponse>> {

        return flow {
            emit(ResourceState.Loading())
            val response = apiDataSource.getPost("post_$selectedPostKey.json")

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

    suspend fun getSkills(): Flow<ResourceState<SkillResponse>> {

        return flow {
            emit(ResourceState.Loading())
            val response = apiDataSource.getSkill()

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

    suspend fun getApps(): Flow<ResourceState<AppResponse>> {

        return flow {
            emit(ResourceState.Loading())
            val response = apiDataSource.getApp()

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