package com.translator.data.local

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history ORDER BY id DESC")
    suspend fun getHistory(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistoryItem(item: HistoryEntity): Long

    @Delete
    suspend fun removeFromHistory(item: HistoryEntity)

    @Query("DELETE FROM history")
    suspend fun clearHistory()
}
