package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedHistoryRepository

class UpdateItemsUseCase(
    private val repository: TranslatedHistoryRepository
    ) {

    suspend operator fun invoke(list: List<Item>): List<Item> {

        return repository.updateItems(list)
    }
}