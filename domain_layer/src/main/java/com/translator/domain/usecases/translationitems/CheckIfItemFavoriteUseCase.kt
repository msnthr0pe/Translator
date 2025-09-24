package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedHistoryRepository

class CheckIfItemFavoriteUseCase (
    private val repository: TranslatedHistoryRepository
) {
    suspend operator fun invoke(item: Item): Boolean {
        return repository.checkIfFavorite(item)
    }
}