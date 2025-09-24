package com.translator.data.repository

import com.translator.data.local.ItemDao
import com.translator.data.local.HistoryEntity
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dao: ItemDao
) : TranslatedItemsRepository {

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

    override suspend fun updateItems(items: List<Item>): List<Item> {
        dao.updateAll(items.map { HistoryEntity(it.id, it.originalWord,
            it.translatedWord, it.isFavorite) })
        return getItems()
    }

    override suspend fun getItems(): List<Item> {
        return dao.getHistory().map { HistoryItem(it.id, it.originalWord,
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
