package com.translator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class TranslationDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

}
