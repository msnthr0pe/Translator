package com.translator.domain.usecases.translationitems.favorites

import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository

class AddToFavoritesUseCase(
    private val repository: FavoritesRepository
    ) {

    suspend operator fun invoke(item: Item): List<Item> {

        return repository.addItem(item)
    }
}