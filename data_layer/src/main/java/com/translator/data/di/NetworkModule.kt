package com.translator.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dictionary.skyeng.ru/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        TODO("Finish implementing")
        return OkHttpClient.Builder().build()
    }
}