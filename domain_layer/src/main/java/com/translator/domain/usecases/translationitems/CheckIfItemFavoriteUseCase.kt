package com.translator.domain.usecases.translationitems

import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository

class CheckIfItemFavoriteUseCase (
    private val repository: TranslatedItemsRepository
) {
    suspend operator fun invoke(item: Item): Boolean {
        return repository.checkIfFavorite(item)
    }
}