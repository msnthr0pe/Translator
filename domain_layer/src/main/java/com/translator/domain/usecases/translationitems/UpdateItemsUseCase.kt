package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class UpdateItemsUseCase(
    private val repository: TranslatedItemsRepository
    ) {

    suspend operator fun invoke(list: List<Item>): List<Item> {

        return repository.updateItems(list)
    }
}