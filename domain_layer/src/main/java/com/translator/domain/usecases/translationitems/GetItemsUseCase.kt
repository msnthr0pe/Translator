package com.translator.domain.usecases.translationitems

import com.translator.domain.StorageType
import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository

class GetItemsUseCase(
    private val historyRepository: HistoryRepository,
    private val favoritesRepository: FavoritesRepository,
    ) {
    suspend operator fun invoke(storageType: StorageType): List<Item> {
        return when(storageType) {
            StorageType.History -> historyRepository.getItems()
            StorageType.Favorites -> favoritesRepository.getItems()

        }
    }
}