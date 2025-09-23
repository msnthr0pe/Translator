package com.translator.domain.usecases.translationitems

import com.translator.domain.StorageType
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class RemoveFromItemsUseCase(
    private val repositories: Map<StorageType, TranslatedItemsRepository>
    ){
    suspend operator fun invoke(item: Item, storageType: StorageType): List<Item> {
        val repository = repositories[storageType] ?: throw IllegalArgumentException("Unknown storage type: $storageType")
        return repository.removeFromItems(item)
    }
}