package com.translator.data.local

import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM history ORDER BY id DESC")
    suspend fun getHistory(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistoryItem(item: HistoryEntity): Long

    @Delete
    suspend fun removeFromHistory(item: HistoryEntity)

    @Query("DELETE FROM history")
    suspend fun clearHistory()

    @Query("SELECT * FROM favorites ORDER BY id DESC")
    suspend fun getFavorites(): List<FavoritesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritesItem(item: FavoritesEntity): Long

    @Delete
    suspend fun removeFromFavorites(item: FavoritesEntity)

    @Query("DELETE FROM favorites")
    suspend fun clearFavorites()


}
