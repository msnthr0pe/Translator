package com.translator.domain.usecases.translationitems.history

import com.translator.domain.models.Item
import com.translator.domain.repository.HistoryRepository

class ClearItemsUseCase(
    private val repository: HistoryRepository
    ) {
    suspend operator fun invoke(): List<Item> {
        return repository.clearItems()
    }
}