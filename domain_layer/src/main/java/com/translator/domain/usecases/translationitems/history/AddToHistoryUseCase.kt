package com.translator.domain.usecases.translationitems.history

import com.translator.domain.models.Item
import com.translator.domain.repository.HistoryRepository
import com.translator.domain.usecases.translationitems.favorites.GetFavoritesUseCase

class AddToHistoryUseCase(
    private val historyRepository: HistoryRepository,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    ) {

    suspend operator fun invoke(item: Item): List<Item> {

        return if (checkIfFavorite(item)) {
            historyRepository.addItem(
                item.setFavorite(true)
            )
        } else {
            historyRepository.addItem(
                item
            )
        }
    }

    private suspend fun checkIfFavorite(queryItem: Item): Boolean {
        val favorites = getFavoritesUseCase()
        return favorites.any { it.originalWord == queryItem.originalWord }
    }
}