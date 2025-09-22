package com.translator.domain.usecases

import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class RemoveFromItemsUseCase(
    private val repository: TranslatedItemsRepository
){
    suspend operator fun invoke(item: HistoryItem): List<Item> {
        return repository.removeFromItems(item)
    }
}