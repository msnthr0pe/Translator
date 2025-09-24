package com.translator.data.local

import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM history ORDER BY id DESC")
    suspend fun getHistory(): List<HistoryEntity>

    @Query("SELECT * FROM history WHERE originalWord = :originalWord LIMIT 1")
    suspend fun getItem(originalWord: String): HistoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistoryItem(item: HistoryEntity): Long

    @Delete
    suspend fun removeFromHistory(item: HistoryEntity)

    @Query("DELETE FROM history")
    suspend fun clearHistory()

    @Update
    suspend fun updateAll(items: List<HistoryEntity>)

}
