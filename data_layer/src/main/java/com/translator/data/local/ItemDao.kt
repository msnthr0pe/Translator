package com.translator.data.local

import androidx.room.*

//Создал отдельные две таблицы для истории и избранного для того,
//чтобы избранное меньше зависило от истории. Например чтобы
//можно было добавить в избранное элемент истории, а при его удалении из истории
//избежать его удаления из избранного
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

    @Query("""
    UPDATE history 
    SET translatedWord = :translatedWord, isFavorite = :isFavorite 
    WHERE originalWord = :originalWord
""")
    suspend fun updateHistoryItem(
        originalWord: String,
        translatedWord: String,
        isFavorite: Boolean
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritesItem(item: FavoritesEntity)

    @Query("SELECT * FROM favorites ORDER BY rowid DESC")
    suspend fun getFavorites(): List<FavoritesEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE originalWord = :originalWord LIMIT 1)")
    suspend fun isFavorite(originalWord: String): Boolean

    @Query("DELETE FROM favorites WHERE originalWord = :originalWord")
    suspend fun removeFromFavorites(originalWord: String)

    @Query("DELETE FROM favorites")
    suspend fun clearFavorites()

    @Update
    suspend fun updateAllFavorites(items: List<FavoritesEntity>)


}
