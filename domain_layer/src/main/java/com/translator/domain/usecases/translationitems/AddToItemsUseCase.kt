package com.translator.domain.usecases.translationitems

import com.translator.domain.StorageType
import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.Item
import com.translator.domain.models.ItemTransfer
import com.translator.domain.repository.TranslatedItemsRepository

class AddToItemsUseCase(
    private val repositories: Map<StorageType, TranslatedItemsRepository>
    ) {

    suspend operator fun invoke(translation: CompleteTranslation,  storageType: StorageType): List<Item> {

        val item = "${translation.originalWord} -> ${translation.translatedWord}"

        val repository = repositories[storageType] ?: throw IllegalArgumentException("Unknown storage type: $storageType")
        return repository.addItem(ItemTransfer(contents = item))
    }
}