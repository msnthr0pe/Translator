package com.translator.domain.usecases.translationitems.history

import com.translator.domain.models.Item
import com.translator.domain.repository.HistoryRepository

class AddToItemsUseCase(
    private val repository: HistoryRepository
    ) {

    suspend operator fun invoke(item: Item): List<Item> {

        return repository.addItem(item)
    }
}