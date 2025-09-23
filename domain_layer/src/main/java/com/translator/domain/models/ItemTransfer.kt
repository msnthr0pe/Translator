package com.translator.domain.models

data class ItemTransfer(
    override val id: Int = -1,
    override val contents: String,
) : Item