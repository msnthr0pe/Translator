package com.translator.di

import com.translator.domain.repository.TranslatedItemsRepository
import com.translator.domain.repository.TranslationRepository
import com.translator.domain.usecases.AddToItemsUseCase
import com.translator.domain.usecases.ClearItemsUseCase
import com.translator.domain.usecases.GetItemsUseCase
import com.translator.domain.usecases.RemoveFromItemsUseCase
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
    fun provideAddToItemsUseCase(
        repository: TranslatedItemsRepository
    ): AddToItemsUseCase = AddToItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideClearItemsUseCase(
        repository: TranslatedItemsRepository
    ): ClearItemsUseCase = ClearItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromItemsUseCase(
        repository: TranslatedItemsRepository
    ): RemoveFromItemsUseCase = RemoveFromItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        repository: TranslatedItemsRepository
    ): GetItemsUseCase = GetItemsUseCase(repository)

}