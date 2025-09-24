package com.translator.domain.usecases.translationitems.history

import com.translator.domain.models.Item
import com.translator.domain.repository.HistoryRepository

class CheckIfItemFavoriteUseCase (
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(item: Item): Boolean {
        return repository.checkIfFavorite(item)
    }
}