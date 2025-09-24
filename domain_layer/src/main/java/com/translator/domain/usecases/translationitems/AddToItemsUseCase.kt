package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedHistoryRepository

class AddToItemsUseCase(
    private val repository: TranslatedHistoryRepository
    ) {

    suspend operator fun invoke(item: Item): List<Item> {

        return repository.addItem(item)
    }
}