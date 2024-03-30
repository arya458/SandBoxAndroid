package com.arya.danesh.myresume.di.module

import com.arya.danesh.myresume.data.dataSource.AppDataSource
import com.arya.danesh.myresume.data.dataSource.AppDataSourceImpl
import com.arya.danesh.myresume.data.dataSource.BlogDataSource
import com.arya.danesh.myresume.data.dataSource.BlogDataSourceImpl
import com.arya.danesh.myresume.data.dataSource.PostDataSource
import com.arya.danesh.myresume.data.dataSource.PostDataSourceImpl
import com.arya.danesh.myresume.data.remote.api.ApiService
import com.arya.danesh.myresume.repository.AppsRepository
import com.arya.danesh.myresume.repository.BlogRepository
import com.arya.danesh.myresume.repository.PostRepository
import com.arya.danesh.utilities.CoreUtility.APP_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

        val moshi = Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        return Retrofit
                .Builder()
                .baseUrl(APP_BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
    }





    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAppDataSource(apiService: ApiService): AppDataSource {
        return AppDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesBlogDataSource(apiService: ApiService):BlogDataSource{
        return BlogDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesPostDataSource(apiService: ApiService): PostDataSource {
        return PostDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesAppRepository(appDataSource: AppDataSource):AppsRepository{
        return AppsRepository(appDataSource)
    }

    @Provides
    @Singleton
    fun providesBlogRepository(blogDataSource: BlogDataSource):BlogRepository{
        return BlogRepository(blogDataSource)
    }

    @Provides
    @Singleton
    fun providesPostRepository(postDataSource: PostDataSource): PostRepository {
        return PostRepository(postDataSource)
    }

}