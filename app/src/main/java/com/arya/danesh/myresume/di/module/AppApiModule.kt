package com.arya.danesh.myresume.di.module

import com.arya.danesh.myresume.data.dataSource.ApiDataSource
import com.arya.danesh.myresume.data.dataSource.ApiDataSourceImpl
import com.arya.danesh.myresume.data.remote.api.ApiService
import com.arya.danesh.myresume.repository.ApiRepository
import com.arya.danesh.utilities.CoreUtility.APP_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppApiModule {

    @Provides
    @Singleton
    fun providesRetrofit():Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            readTimeout(60,TimeUnit.SECONDS)
        }

//        val moshi = Moshi
//                .Builder()
//                .add(KotlinJsonAdapterFactory())
//                .build()

        return Retrofit
                .Builder()
                .baseUrl(APP_BASE_URL)
                .client(httpClient.build())
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }





    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAppDataSource(apiService: ApiService): ApiDataSource {
        return ApiDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesBlogRepository(apiDataSource: ApiDataSource):ApiRepository{
        return ApiRepository(apiDataSource)
    }

}