package com.translator.di

import com.translator.domain.repository.TranslatedHistoryRepository
import com.translator.domain.repository.TranslationRepository
import com.translator.domain.usecases.translationitems.AddToItemsUseCase
import com.translator.domain.usecases.translationitems.ClearItemsUseCase
import com.translator.domain.usecases.translationitems.GetItemsUseCase
import com.translator.domain.usecases.translationitems.RemoveFromItemsUseCase
import com.translator.domain.usecases.translation.TranslateUseCase
import com.translator.domain.usecases.translationitems.CheckIfItemFavoriteUseCase
import com.translator.domain.usecases.translationitems.UpdateItemsUseCase
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
        repository: TranslatedHistoryRepository
        ): AddToItemsUseCase = AddToItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideClearItemsUseCase(
        repository: TranslatedHistoryRepository
    ): ClearItemsUseCase = ClearItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromItemsUseCase(
        repository: TranslatedHistoryRepository
    ): RemoveFromItemsUseCase = RemoveFromItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        repository: TranslatedHistoryRepository
    ): GetItemsUseCase = GetItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateItemsUseCase(
        repository: TranslatedHistoryRepository
    ): UpdateItemsUseCase = UpdateItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideCheckIfItemFavoriteUseCase(
        repository: TranslatedHistoryRepository
    ): CheckIfItemFavoriteUseCase = CheckIfItemFavoriteUseCase(repository)

}