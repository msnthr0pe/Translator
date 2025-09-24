package com.translator.di

import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository
import com.translator.domain.repository.TranslationRepository
import com.translator.domain.usecases.translationitems.history.AddToHistoryUseCase
import com.translator.domain.usecases.translationitems.history.ClearHistoryUseCase
import com.translator.domain.usecases.translationitems.history.GetHistoryUseCase
import com.translator.domain.usecases.translationitems.history.RemoveFromHistoryUseCase
import com.translator.domain.usecases.translation.TranslateUseCase
import com.translator.domain.usecases.translationitems.favorites.AddToFavoritesUseCase
import com.translator.domain.usecases.translationitems.favorites.ClearFavoritesUseCase
import com.translator.domain.usecases.translationitems.favorites.GetFavoritesUseCase
import com.translator.domain.usecases.translationitems.favorites.RemoveFromFavoritesUseCase
import com.translator.domain.usecases.translationitems.history.CheckIfHistoryItemIsFavoriteUseCase
import com.translator.domain.usecases.translationitems.history.UpdateHistoryUseCase
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
        repository: HistoryRepository,
        getFavoritesUseCase: GetFavoritesUseCase
        ): AddToHistoryUseCase = AddToHistoryUseCase(
        repository,
            getFavoritesUseCase)

    @Provides
    @Singleton
    fun provideClearItemsUseCase(
        repository: HistoryRepository
    ): ClearHistoryUseCase = ClearHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromHistoryUseCase(
        repository: HistoryRepository
    ): RemoveFromHistoryUseCase = RemoveFromHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        repository: HistoryRepository
    ): GetHistoryUseCase = GetHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateHistoryUseCase(
        repository: HistoryRepository
    ): UpdateHistoryUseCase = UpdateHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideCheckIfHistoryItemIsFavoriteUseCase(
        repository: HistoryRepository
    ): CheckIfHistoryItemIsFavoriteUseCase = CheckIfHistoryItemIsFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideAddToFavoritesUseCase(
        repository: FavoritesRepository
    ): AddToFavoritesUseCase = AddToFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromFavoritesUseCase(
        repository: FavoritesRepository
    ): RemoveFromFavoritesUseCase = RemoveFromFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(
        repository: FavoritesRepository
    ): GetFavoritesUseCase = GetFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideClearFavoritesUseCase(
        repository: FavoritesRepository
    ): ClearFavoritesUseCase = ClearFavoritesUseCase(repository)


}