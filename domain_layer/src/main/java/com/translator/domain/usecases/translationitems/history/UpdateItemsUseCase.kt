package com.translator.domain.usecases.translationitems.history

import com.translator.domain.models.Item
import com.translator.domain.repository.HistoryRepository

class UpdateItemsUseCase(
    private val repository: HistoryRepository
    ) {

    suspend operator fun invoke(list: List<Item>): List<Item> {

        return repository.updateItems(list)
    }
}