package com.translator.domain.models.translationmodels

data class ResponseDTO (
    val text: String,
    val meanings: List<MeaningDTO>,
)