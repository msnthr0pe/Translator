package com.translator.domain.repository

import com.translator.domain.models.TranslationRequest
import com.translator.domain.models.TranslatedWord

interface TranslationRepository {
    suspend fun translateWord(translationRequest: TranslationRequest): TranslatedWord
    suspend fun addToFavorites(translatedWord: TranslatedWord)
    suspend fun getFavorites(): List<TranslatedWord>
    suspend fun removeFromFavorites(translatedWord: TranslatedWord)
    suspend fun clearFavorites()
}