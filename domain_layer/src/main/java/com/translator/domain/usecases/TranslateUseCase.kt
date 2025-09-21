package com.translator.domain.usecases

import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.TranslationRequest
import com.translator.domain.repository.TranslationRepository

class TranslateUseCase(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(translationRequest: TranslationRequest): TranslatedWord {
        return repository.translateWord(translationRequest)
    }
}