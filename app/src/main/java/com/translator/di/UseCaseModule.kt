package com.translator.di

import com.translator.domain.repository.TranslatedItemsRepository
import com.translator.domain.repository.TranslationRepository
import com.translator.domain.usecases.AddToHistoryUseCase
import com.translator.domain.usecases.ClearHistoryUseCase
import com.translator.domain.usecases.GetHistoryUseCase
import com.translator.domain.usecases.RemoveFromHistoryUseCase
import com.translator.domain.usecases.TranslateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        repository: TranslationRepository
    ): TranslateUseCase = TranslateUseCase(repository)

    @Provides
    @Singleton
    fun provideAddToHistoryUseCase(
        repository: TranslatedItemsRepository
    ): AddToHistoryUseCase = AddToHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideClearHistoryUseCase(
        repository: TranslatedItemsRepository
    ): ClearHistoryUseCase = ClearHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromHistoryUseCase(
        repository: TranslatedItemsRepository
    ): RemoveFromHistoryUseCase = RemoveFromHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        repository: TranslatedItemsRepository
    ): GetHistoryUseCase = GetHistoryUseCase(repository)

}