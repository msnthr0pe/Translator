package com.translator.di

import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository
import com.translator.domain.repository.TranslationRepository
import com.translator.domain.usecases.translationitems.history.AddToItemsUseCase
import com.translator.domain.usecases.translationitems.history.ClearItemsUseCase
import com.translator.domain.usecases.translationitems.history.GetItemsUseCase
import com.translator.domain.usecases.translationitems.history.RemoveFromItemsUseCase
import com.translator.domain.usecases.translation.TranslateUseCase
import com.translator.domain.usecases.translationitems.favorites.AddToFavoritesUseCase
import com.translator.domain.usecases.translationitems.history.CheckIfItemFavoriteUseCase
import com.translator.domain.usecases.translationitems.history.UpdateItemsUseCase
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
        repository: HistoryRepository
        ): AddToItemsUseCase = AddToItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideClearItemsUseCase(
        repository: HistoryRepository
    ): ClearItemsUseCase = ClearItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromItemsUseCase(
        repository: HistoryRepository
    ): RemoveFromItemsUseCase = RemoveFromItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        repository: HistoryRepository
    ): GetItemsUseCase = GetItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateItemsUseCase(
        repository: HistoryRepository
    ): UpdateItemsUseCase = UpdateItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideCheckIfItemFavoriteUseCase(
        repository: HistoryRepository
    ): CheckIfItemFavoriteUseCase = CheckIfItemFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideAddToFavoritesUseCase(
        repository: FavoritesRepository
    ): AddToFavoritesUseCase = AddToFavoritesUseCase(repository)

}