package com.translator.domain.repository

import com.translator.domain.models.Item

//От этого интерфейса наследуются репозитории истории и избранного
//Это было сделано для повышения гибкости кода, потому что реализация
//репозиториев истории и избранного отличается и может меняться в будущем
interface TranslatedItemsRepository {
    suspend fun addItem(item: Item): List<Item>
    suspend fun updateItemByWord(item: Item): List<Item>
    suspend fun getItems(): List<Item>
    suspend fun removeFromItems(item: Item): List<Item>
    suspend fun clearItems(): List<Item>
}