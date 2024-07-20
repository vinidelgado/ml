package com.vdelgado.ml.di

import com.vdelgado.ml.Constants.BASE_URL
import com.vdelgado.ml.data.remote.MLServiceApi
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import com.vdelgado.ml.data.repository.MLSearchProductRepositoryImpl
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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
    fun provideSearchProductUseCase(repository: MLSearchProductRepository) =
        MLSearchProductUseCase(repository)
}