package com.translator.data.repository

import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.TranslationRequest
import com.translator.domain.repository.TranslationRepository

class TranslationRepositoryImpl : TranslationRepository {
    override suspend fun translateWord(translationRequest: TranslationRequest): TranslatedWord {
        TODO("Not yet implemented")
    }

    override suspend fun addToHistory(translatedWord: TranslatedWord) {
        TODO("Not yet implemented")
    }

    override suspend fun getHistory(): List<TranslatedWord> {
        TODO("Not yet implemented")
    }

    override suspend fun clearHistory() {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(translatedWord: TranslatedWord) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<TranslatedWord> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorites(translatedWord: TranslatedWord) {
        TODO("Not yet implemented")
    }

    override suspend fun clearFavorites() {
        TODO("Not yet implemented")
    }
}