package com.translator.data.models

data class ResponseDTO (
    val text: String,
    val meanings: List<MeaningDTO>,
)