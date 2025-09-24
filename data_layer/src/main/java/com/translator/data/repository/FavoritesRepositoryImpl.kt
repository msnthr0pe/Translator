package com.translator.data.repository

import com.translator.data.local.FavoritesEntity
import com.translator.data.local.ItemDao
import com.translator.domain.models.QueryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: ItemDao
) : FavoritesRepository {

    override suspend fun addItem(item: Item): List<Item> {
        dao.addFavoritesItem(
            FavoritesEntity(
                originalWord = item.originalWord,
                translatedWord = item.translatedWord,
                isFavorite = true
            )
        )
        return getItems()
    }

    override suspend fun updateItemByWord(item: Item): List<Item> {
        dao.addFavoritesItem(
            FavoritesEntity(
                originalWord = item.originalWord,
                translatedWord = item.translatedWord,
                isFavorite = item.isFavorite
            )
        )
        return getItems()
    }

    override suspend fun getItems(): List<Item> {
        return dao.getFavorites().map {
            QueryItem(
                originalWord = it.originalWord,
                translatedWord = it.translatedWord,
                isFavorite = it.isFavorite,
            )
        }
    }

    override suspend fun removeFromItems(item: Item): List<Item> {
        dao.removeFromFavorites(item.originalWord)
        return getItems()
    }

    override suspend fun clearItems(): List<Item> {
        dao.clearFavorites()
        return getItems()
    }
}

