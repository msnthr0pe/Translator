package com.translator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey val originalWord: String,
    val translatedWord: String,
    val isFavorite: Boolean,
)

