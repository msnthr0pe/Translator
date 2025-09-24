package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedHistoryRepository

class GetItemsUseCase(
    private val repository: TranslatedHistoryRepository
    ) {
    suspend operator fun invoke(): List<Item> {
        return repository.getItems()
    }
}