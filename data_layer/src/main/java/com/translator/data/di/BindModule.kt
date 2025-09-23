package com.translator.data.di

import com.translator.data.repository.FavoritesRepositoryImpl
import com.translator.data.repository.HistoryRepositoryImpl
import com.translator.data.local.ItemDao
import com.translator.domain.StorageType
import com.translator.domain.repository.TranslatedItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BindModule {

    @Provides
    @Singleton
    @IntoMap
    @StorageTypeKey(StorageType.HISTORY)
    fun provideHistoryRepository(
        dao: ItemDao
    ): TranslatedItemsRepository = HistoryRepositoryImpl(dao)

    @Provides
    @Singleton
    @IntoMap
    @StorageTypeKey(StorageType.FAVORITES)
    fun provideFavoritesRepository(
        dao: ItemDao
    ): TranslatedItemsRepository = FavoritesRepositoryImpl(dao)
}
