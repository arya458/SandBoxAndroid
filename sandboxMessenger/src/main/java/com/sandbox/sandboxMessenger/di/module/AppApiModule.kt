package com.sandbox.sandboxMessenger.di.module

import com.sandbox.sandboxMessenger.data.dataSource.ChatDataSource
import com.sandbox.sandboxMessenger.data.dataSource.ChatDataSourceImpl
import com.arya.danesh.utilities.CoreUtility.CHAT_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.http.Url
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppApiModule {


    @Provides
    @Singleton
    fun matrixRestClient(): MatrixClientServerApiClientImpl {
        return MatrixClientServerApiClientImpl(
                baseUrl = Url(CHAT_BASE_URL),
//                syncLoopDelay = Duration.parse("1000"),
//                syncLoopErrorDelay = Duration.parse("1000"),
        )
    }


    @Provides
    @Singleton
    fun providesChatDataSource(matrix : MatrixClientServerApiClientImpl): ChatDataSource {
        return ChatDataSourceImpl(matrix)
    }




}