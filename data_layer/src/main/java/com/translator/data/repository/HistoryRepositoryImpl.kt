package com.translator.data.repository

import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository

class HistoryRepositoryImpl : HistoryRepository {

    private val historyItems = mutableListOf<HistoryItem>()

    override suspend fun addHistoryItem(historyItem: HistoryItem): List<HistoryItem> {
        historyItems.add(historyItem)
        return getHistory()
    }

    private fun getHistory(): List<HistoryItem> {
        return historyItems.reversed()
    }

    override suspend fun removeFromHistory(item: HistoryItem): List<HistoryItem> {
        historyItems.remove(item)
        return getHistory()
    }

    override suspend fun clearHistory(): List<HistoryItem> {
        historyItems.clear()
        return getHistory()
    }
}