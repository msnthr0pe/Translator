package com.translator.domain.usecases.translationitems

import com.translator.domain.StorageType
import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository

class RemoveFromItemsUseCase(
    private val historyRepository: HistoryRepository,
    private val favoritesRepository: FavoritesRepository,
    ){
    suspend operator fun invoke(item: Item, storageType: StorageType): List<Item> {
        return when (storageType) {
            StorageType.History -> historyRepository.removeFromItems(item)
            StorageType.Favorites -> favoritesRepository.removeFromItems(item)
        }
    }
}