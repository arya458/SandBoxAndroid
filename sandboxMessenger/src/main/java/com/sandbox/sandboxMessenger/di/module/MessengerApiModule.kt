package com.sandbox.sandboxMessenger.di.module

import android.app.Application
import com.sandbox.sandboxMessenger.data.dataSource.MessengerDataSource
import com.sandbox.sandboxMessenger.data.dataSource.MessengerDataSourceImpl
import com.arya.danesh.utilities.CoreUtility.CHAT_BASE_URL
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.safetynet.SafetyNetClient
import com.sandbox.sandboxMessenger.data.dao.MessengerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.http.Url
import net.folivo.trixnity.clientserverapi.client.MatrixClientServerApiClientImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MessengerApiModule {


    @Provides
    @Singleton
    fun matrixRestClient(): MatrixClientServerApiClientImpl {
        return MatrixClientServerApiClientImpl(
                baseUrl = Url(CHAT_BASE_URL))
    }


    @Provides
    @Singleton
    fun providesChatDataSource(matrix: MatrixClientServerApiClientImpl,applicationContext:Application): MessengerDataSource {
        return MessengerDataSourceImpl(matrix,applicationContext)
    }


    @Provides
    @Singleton
    fun getAppDataBase(applicationContext: Application): AppDatabase {
        return AppDatabase.getDatabase(applicationContext)
    }

    @Provides
    @Singleton
    fun getMessenger(appDatabase: AppDatabase): MessengerDao {
        return appDatabase.MessengerDao()
    }


}