package com.translator.domain.usecases.translationitems.favorites

import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository

class RemoveFromFavoritesUseCase(
    private val repository: FavoritesRepository
    ){
    suspend operator fun invoke(item: Item): List<Item> {
        return repository.removeFromItems(item)
    }
}