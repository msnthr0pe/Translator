package com.translator.domain.usecases

import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository

class AddToHistoryUseCase(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(translation: CompleteTranslation): List<HistoryItem> {
        return repository.addHistoryItem(translation)
    }
}