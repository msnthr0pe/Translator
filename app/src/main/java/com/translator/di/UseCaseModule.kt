package com.translator.di

import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository
import com.translator.domain.repository.TranslationRepository
import com.translator.domain.usecases.translationitems.AddToItemsUseCase
import com.translator.domain.usecases.translationitems.ClearItemsUseCase
import com.translator.domain.usecases.translationitems.GetItemsUseCase
import com.translator.domain.usecases.translationitems.RemoveFromItemsUseCase
import com.translator.domain.usecases.translation.TranslateUseCase
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
        historyRepository: HistoryRepository,
        favoritesRepository: FavoritesRepository,
        ): AddToItemsUseCase = AddToItemsUseCase(historyRepository, favoritesRepository)

    @Provides
    @Singleton
    fun provideClearItemsUseCase(
        historyRepository: HistoryRepository,
        favoritesRepository: FavoritesRepository,
        ): ClearItemsUseCase = ClearItemsUseCase(historyRepository, favoritesRepository)

    @Provides
    @Singleton
    fun provideRemoveFromItemsUseCase(
        historyRepository: HistoryRepository,
        favoritesRepository: FavoritesRepository,
        ): RemoveFromItemsUseCase = RemoveFromItemsUseCase(historyRepository, favoritesRepository)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        historyRepository: HistoryRepository,
        favoritesRepository: FavoritesRepository,
        ): GetItemsUseCase = GetItemsUseCase(historyRepository, favoritesRepository)

}