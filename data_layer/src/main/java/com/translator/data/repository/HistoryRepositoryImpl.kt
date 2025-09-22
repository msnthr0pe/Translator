package com.translator.data.repository

import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository

class HistoryRepositoryImpl : HistoryRepository {

    private var nextHistoryItemID: Int = 0
    private val historyItems = mutableListOf<HistoryItem>()

    override suspend fun addHistoryItem(translation: CompleteTranslation): List<HistoryItem> {
        val item = HistoryItem(nextHistoryItemID++,
            "${translation.originalWord} -> ${translation.translatedWord}")
        historyItems.add(item)
        return getHistory()
    }

    fun getHistory(): List<HistoryItem> {
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