package com.translator.domain.models

data class FavoritesItem(
    override val id: Int = -1,
    override val contents: String,
) : Item