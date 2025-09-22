package com.translator.domain.repository

import com.translator.domain.models.TranslationRequest
import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.translationmodels.ResponseDTO

interface TranslationRepository {
    suspend fun getWordTranslation(translationRequest: TranslationRequest): List<ResponseDTO>
    suspend fun addToFavorites(translatedWord: TranslatedWord)
    suspend fun getFavorites(): List<TranslatedWord>
    suspend fun removeFromFavorites(translatedWord: TranslatedWord)
    suspend fun clearFavorites()
}