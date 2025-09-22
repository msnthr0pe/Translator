package com.translator.data.repository

import com.translator.data.remote.SkyengApi
import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.TranslationRequest
import com.translator.domain.models.translationmodels.ResponseDTO
import com.translator.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val skyengApi: SkyengApi
) : TranslationRepository {
    override suspend fun getWordTranslation(translationRequest: TranslationRequest): List<ResponseDTO> {
        val response = skyengApi.searchForWord(translationRequest.word)
        return response
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