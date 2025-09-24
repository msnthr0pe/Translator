package com.translator.data.di

import android.content.Context
import androidx.room.Room
import com.translator.data.local.ItemDao
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
        return Room.databaseBuilder(context, TranslationDatabase::class.java, "translation_db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideHistoryDao(db: TranslationDatabase): ItemDao = db.itemDao()
}
