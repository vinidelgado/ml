package com.vdelgado.ml.di

import android.content.Context
import com.vdelgado.ml.data.remote.MLProductItemRepositoryImpl
import com.vdelgado.ml.data.remote.common.LiveNetworkMonitor
import com.vdelgado.ml.data.remote.common.MLNetworkMonitorInterceptor
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.data.remote.common.NetworkMonitor
import com.vdelgado.ml.data.repository.MLSearchProductRepositoryImpl
import com.vdelgado.ml.domain.repository.MLProductItemRepository
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import com.vdelgado.ml.domain.usecase.product.MLProductItemUseCase
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MLAppModule {
    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext appContext: Context
    ): NetworkMonitor {
        return LiveNetworkMonitor(appContext)
    }

    @Provides
    fun provideRetrofit(liveNetworkMonitor: NetworkMonitor,url:String): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(MLNetworkMonitorInterceptor(liveNetworkMonitor))
            .build()
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideMLServiceApi(
        retrofit: Retrofit
    ): MLServiceApi = retrofit.create(MLServiceApi::class.java)

    @Provides
    @Singleton
    fun provideMLSearchProductRepository(mlServiceApi: MLServiceApi): MLSearchProductRepository =
        MLSearchProductRepositoryImpl(mlServiceApi)

    @Provides
    @Singleton
    fun provideMLProductItemRepository(mlServiceApi: MLServiceApi): MLProductItemRepository =
        MLProductItemRepositoryImpl(mlServiceApi)

    @Provides
    @Singleton
    fun provideSearchProductUseCase(repository: MLSearchProductRepository) =
        MLSearchProductUseCase(repository)

    @Provides
    @Singleton
    fun provideMLItemProductUseCase(repository: MLProductItemRepository) =
        MLProductItemUseCase(repository)
}