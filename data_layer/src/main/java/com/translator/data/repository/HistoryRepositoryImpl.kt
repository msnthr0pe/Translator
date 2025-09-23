package com.translator.data.repository

import com.translator.data.local.ItemDao
import com.translator.data.local.HistoryEntity
import com.translator.domain.models.HistoryItem
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
                translatedWord = item.translatedWord
            )
        )
        return getItems()
    }

    override suspend fun getItems(): List<Item> {
        return dao.getHistory().map { HistoryItem(it.id, it.originalWord,
            it.translatedWord) }
    }

    override suspend fun removeFromItems(item: Item): List<Item> {
        dao.removeFromHistory(HistoryEntity(item.id, item.originalWord,
            item.translatedWord))
        return getItems()
    }

    override suspend fun clearItems(): List<Item> {
        dao.clearHistory()
        return getItems()
    }
}
