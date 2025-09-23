package com.translator.domain.models

interface Item {
    val id: Int
    val originalWord: String
    val translatedWord: String
    val isFavorite: Boolean

    fun toggleFavorite(): Item
}