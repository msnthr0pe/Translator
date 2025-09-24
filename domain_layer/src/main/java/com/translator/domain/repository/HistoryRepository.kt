package com.translator.domain.repository

import com.translator.domain.models.Item

interface HistoryRepository : TranslatedItemsRepository {
    suspend fun checkIfFavorite(item: Item): Boolean
}