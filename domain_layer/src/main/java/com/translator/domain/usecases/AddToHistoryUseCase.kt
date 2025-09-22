package com.translator.domain.usecases

import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository

class AddToHistoryUseCase(
    private val repository: HistoryRepository
) {
    private var nextHistoryItemID: Int = 0

    suspend operator fun invoke(translation: CompleteTranslation): List<HistoryItem> {

        val item = HistoryItem(nextHistoryItemID++,
            "${translation.originalWord} -> ${translation.translatedWord}")

        return repository.addHistoryItem(item)
    }
}