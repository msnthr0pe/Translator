package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class ClearItemsUseCase(
    private val repository: TranslatedItemsRepository
    ) {
    suspend operator fun invoke(): List<Item> {
        return repository.clearItems()
    }
}