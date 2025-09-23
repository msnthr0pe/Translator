package com.translator.domain.models

data class FavoritesItem(
    override val id: Int = -1,
    override val originalWord: String,
    override val translatedWord: String,
) : Item