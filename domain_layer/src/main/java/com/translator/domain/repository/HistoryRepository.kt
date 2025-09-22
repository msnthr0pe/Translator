package com.translator.domain.repository

import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem

interface HistoryRepository {
    suspend fun addHistoryItem(translation: CompleteTranslation): List<HistoryItem>
    suspend fun removeFromHistory(item: HistoryItem): List<HistoryItem>
    suspend fun clearHistory(): List<HistoryItem>
}