package com.translator.data.repository

import com.translator.data.local.ItemDao
import com.translator.data.local.HistoryEntity
import com.translator.domain.models.QueryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dao: ItemDao
) : HistoryRepository {

    override suspend fun addItem(item: Item): List<Item> {
        dao.addHistoryItem(
            HistoryEntity(
                originalWord = item.originalWord,
                translatedWord = item.translatedWord,
                isFavorite = item.isFavorite
            )
        )
        return getItems()
    }

    override suspend fun updateItemByWord(item: Item): List<Item> {
        dao.updateHistoryItem(
            item.originalWord,
            item.translatedWord,
            item.isFavorite
        )
        return getItems()
    }

    override suspend fun checkIfFavorite(item: Item): Boolean {
        val returnedItem = dao.getItem(item.originalWord)
        if (returnedItem == null) {
            return false
        }
//        if (returnedItem.isFavorite) {
//            dao.addFavoritesItem(FavoritesEntity(
//                originalWord = item.originalWord,
//                translatedWord = item.translatedWord,
//                isFavorite = true,
//            ))
//        } else {
//            dao.removeFromFavorites(FavoritesEntity(
//                originalWord = item.originalWord,
//                translatedWord = item.translatedWord,
//                isFavorite = false,
//            ))
//        }
        return returnedItem.isFavorite
    }

    override suspend fun getItems(): List<Item> {
        return dao.getHistory().map { QueryItem(it.id, it.originalWord,
            it.translatedWord, it.isFavorite) }
    }

    override suspend fun removeFromItems(item: Item): List<Item> {
        dao.removeFromHistory(HistoryEntity(item.id, item.originalWord,
            item.translatedWord, item.isFavorite))
        return getItems()
    }

    override suspend fun clearItems(): List<Item> {
        dao.clearHistory()
        return getItems()
    }
}
