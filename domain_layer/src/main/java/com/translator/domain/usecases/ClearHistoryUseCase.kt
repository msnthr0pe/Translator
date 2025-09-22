package com.translator.domain.usecases

import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository

class ClearHistoryUseCase(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(): List<HistoryItem> {
        return repository.clearHistory()
    }
}