package com.translator.domain.repository

import com.translator.domain.models.Item

interface TranslatedItemsRepository {
    suspend fun addItem(item: Item): List<Item>
    suspend fun updateItems(items: List<Item>): List<Item>
    suspend fun checkIfFavorite(item: Item): Boolean
    suspend fun getItems(): List<Item>
    suspend fun removeFromItems(item: Item): List<Item>
    suspend fun clearItems(): List<Item>
}