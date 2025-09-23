package com.translator.domain.usecases.translationitems

import com.translator.domain.StorageType
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class ClearItemsUseCase(
    private val repositories: Map<StorageType, TranslatedItemsRepository>
    ) {
    suspend operator fun invoke(storageType: StorageType): List<Item> {
        val repository = repositories[storageType] ?: throw IllegalArgumentException("Unknown storage type: $storageType")
        return repository.clearItems()
    }
}