package com.translator.domain.usecases

import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository

class RemoveFromHistoryUseCase(
    private val repository: HistoryRepository
){
    suspend operator fun invoke(item: HistoryItem): List<HistoryItem> {
        return repository.removeFromHistory(item)
    }
}