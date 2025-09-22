package com.translator.domain.usecases

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class ClearHistoryUseCase(
    private val repository: TranslatedItemsRepository
) {
    suspend operator fun invoke(): List<Item> {
        return repository.clearItems()
    }
}