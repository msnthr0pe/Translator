package com.translator.domain.models

data class HistoryItem(
    override val id: Int = -1,
    override val contents: String,
) : Item