package com.translator.domain.usecases.translationitems.favorites

import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository

class GetFavoritesUseCase(
    private val repository: FavoritesRepository
    ) {
    suspend operator fun invoke(): List<Item> {
        return repository.getItems()
    }
}