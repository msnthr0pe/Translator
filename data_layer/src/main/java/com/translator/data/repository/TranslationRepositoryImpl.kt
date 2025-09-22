package com.translator.data.repository

import com.translator.data.models.ErrorModel
import com.translator.data.remote.SkyengApi
import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.TranslationRequest
import com.translator.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val skyengApi: SkyengApi
) : TranslationRepository {
    override suspend fun translateWord(translationRequest: TranslationRequest): TranslatedWord {
        val response = skyengApi.searchForWord(translationRequest.word)
        //Log.d("TranslatorApp", response.toString())
        if (response.isNotEmpty()) {
            val result = response.first().meanings.last().translation.text
            return TranslatedWord(result)
        }
        return TranslatedWord(ErrorModel(" ").error)
    }

    override suspend fun addToHistory(translatedWord: TranslatedWord) {
        TODO("Not yet implemented")
    }

    override suspend fun getHistory(): List<TranslatedWord> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromHistory(translatedWord: TranslatedWord) {
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