package com.translator.domain.repository

import com.translator.domain.models.TranslationRequest
import com.translator.domain.models.translationmodels.ResponseDTO

interface TranslationRepository {
    suspend fun getWordTranslation(translationRequest: TranslationRequest): List<ResponseDTO>
}