package com.vdelgado.ml.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    open fun getBaseUrl () = "https://api.mercadolibre.com"

    @Provides
    @Singleton
    fun provideUrl(): String = getBaseUrl()

}