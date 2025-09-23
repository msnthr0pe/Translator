package com.translator.data.repository

import com.translator.data.local.FavoritesEntity
import com.translator.data.local.ItemDao
import com.translator.domain.models.FavoritesItem
import com.translator.domain.models.Item
import com.translator.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: ItemDao,
) : FavoritesRepository {

    override suspend fun addItem(item: Item): List<Item> {
        dao.addFavoritesItem(
            FavoritesEntity(
                contents = item.contents
            )
        )
        return getItems()
    }

    override suspend fun getItems(): List<Item> {
        return dao.getFavorites().map { FavoritesItem(it.id, it.contents) }
    }

    override suspend fun removeFromItems(item: Item): List<Item> {
        dao.removeFromFavorites(FavoritesEntity(item.id, item.contents))
        return getItems()
    }

    override suspend fun clearItems(): List<Item> {
        dao.clearFavorites()
        return getItems()
    }
}
