package com.translator.di

import com.translator.domain.StorageType
import com.translator.domain.repository.TranslatedItemsRepository
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
        repositories: Map<StorageType, @JvmSuppressWildcards TranslatedItemsRepository>
        ): AddToItemsUseCase = AddToItemsUseCase(repositories)

    @Provides
    @Singleton
    fun provideClearItemsUseCase(
        repositories: Map<StorageType, @JvmSuppressWildcards TranslatedItemsRepository>
    ): ClearItemsUseCase = ClearItemsUseCase(repositories)

    @Provides
    @Singleton
    fun provideRemoveFromItemsUseCase(
        repositories: Map<StorageType, @JvmSuppressWildcards TranslatedItemsRepository>
    ): RemoveFromItemsUseCase = RemoveFromItemsUseCase(repositories)

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(
        repositories: Map<StorageType, @JvmSuppressWildcards TranslatedItemsRepository>
    ): GetItemsUseCase = GetItemsUseCase(repositories)

}