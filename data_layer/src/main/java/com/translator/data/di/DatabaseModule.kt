package com.translator.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.translator.data.local.HistoryDao
import com.translator.data.local.TranslationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TranslationDatabase {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE history ADD COLUMN newColumn TEXT NOT NULL DEFAULT ''")
            }
        }
        return Room.databaseBuilder(context, TranslationDatabase::class.java, "translation_db")
            .build()
    }

    @Provides
    fun provideHistoryDao(db: TranslationDatabase): HistoryDao = db.historyDao()
}
