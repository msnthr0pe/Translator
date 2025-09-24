package com.translator.data.repository

import com.translator.data.local.FavoritesEntity
import com.translator.data.local.ItemDao
import com.translator.data.local.HistoryEntity
import com.translator.domain.models.QueryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: ItemDao
) : FavoritesRepository {

    override suspend fun addItem(item: Item): List<Item> {
        dao.addFavoritesItem(
            FavoritesEntity(
                originalWord = item.originalWord,
                translatedWord = item.translatedWord,
                isFavorite = item.isFavorite
            )
        )
        return getItems()
    }

    override suspend fun updateItems(items: List<Item>): List<Item> {
        dao.updateAllFavorites(items.map { FavoritesEntity(it.id, it.originalWord,
            it.translatedWord, it.isFavorite) })
        return getItems()
    }

    override suspend fun checkIfFavorite(item: Item): Boolean {
        val returnedItem = dao.getItem(item.originalWord)
        if (returnedItem == null) {
            return false
        }
        if (returnedItem.isFavorite) {
            dao.addFavoritesItem(FavoritesEntity(
                originalWord = item.originalWord,
                translatedWord = item.translatedWord,
                isFavorite = true,
            ))
        } else {
            dao.removeFromFavorites(FavoritesEntity(
                originalWord = item.originalWord,
                translatedWord = item.translatedWord,
                isFavorite = false,
            ))
        }
        return returnedItem.isFavorite
    }

    override suspend fun getItems(): List<Item> {
        return dao.getFavorites().map { QueryItem(it.id, it.originalWord,
            it.translatedWord, it.isFavorite) }
    }

    override suspend fun removeFromItems(item: Item): List<Item> {
        dao.removeFromFavorites(FavoritesEntity(item.id, item.originalWord,
            item.translatedWord, item.isFavorite))
        return getItems()
    }

    override suspend fun clearItems(): List<Item> {
        dao.clearFavorites()
        return getItems()
    }
}
