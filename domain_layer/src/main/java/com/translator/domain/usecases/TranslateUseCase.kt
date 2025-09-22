package com.translator.domain.usecases

import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.TranslationRequest
import com.translator.domain.models.translationmodels.ErrorModel
import com.translator.domain.repository.TranslationRepository

class TranslateUseCase(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(translationRequest: TranslationRequest): TranslatedWord {
        val response = repository.getWordTranslation(translationRequest)

        if (response.isNotEmpty()) {
            val result = response.first().meanings.first().translation.text
            return TranslatedWord(result)
        }
        return TranslatedWord(ErrorModel(" ").error)
    }
}