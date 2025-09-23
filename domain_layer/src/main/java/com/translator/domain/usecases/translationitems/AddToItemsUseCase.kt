package com.translator.domain.usecases.translationitems

import com.translator.domain.StorageType
import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.FavoritesItem
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import com.translator.domain.repository.HistoryRepository

class AddToItemsUseCase(
    private val historyRepository: HistoryRepository,
    private val favoritesRepository: FavoritesRepository,
    ) {

    suspend operator fun invoke(translation: CompleteTranslation,  storageType: StorageType): List<Item> {

        val item = "${translation.originalWord} -> ${translation.translatedWord}"

        return when (storageType) {
            StorageType.History -> historyRepository.addItem(HistoryItem(contents = item))
            StorageType.Favorites -> favoritesRepository.addItem(FavoritesItem(contents = item))
        }
    }
}