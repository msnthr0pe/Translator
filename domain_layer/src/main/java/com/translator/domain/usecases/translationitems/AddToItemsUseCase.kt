package com.translator.domain.usecases.translationitems

import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class AddToItemsUseCase(
    private val repository: TranslatedItemsRepository
    ) {

    suspend operator fun invoke(translation: CompleteTranslation): List<Item> {

        return repository.addItem(HistoryItem(
            originalWord = translation.originalWord,
            translatedWord = translation.translatedWord)
        )
    }
}