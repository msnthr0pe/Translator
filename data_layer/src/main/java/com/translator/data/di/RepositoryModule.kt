package com.translator.data.di

import com.translator.data.repository.HistoryRepositoryImpl
import com.translator.data.local.ItemDao
import com.translator.data.remote.SkyengApi
import com.translator.data.repository.TranslationRepositoryImpl
import com.translator.domain.repository.TranslatedHistoryRepository
import com.translator.domain.repository.TranslationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTranslationRepository(api: SkyengApi): TranslationRepository =
        TranslationRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideHistoryRepository(dao: ItemDao): TranslatedHistoryRepository =
        HistoryRepositoryImpl(dao)

}