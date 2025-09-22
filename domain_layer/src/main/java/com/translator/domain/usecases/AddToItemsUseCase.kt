package com.translator.domain.usecases

import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class AddToItemsUseCase(
    private val repository: TranslatedItemsRepository
) {

    suspend operator fun invoke(translation: CompleteTranslation): List<Item> {

        val item = HistoryItem(contents = "${translation.originalWord} -> ${translation.translatedWord}")

        return repository.addItem(item)
    }
}