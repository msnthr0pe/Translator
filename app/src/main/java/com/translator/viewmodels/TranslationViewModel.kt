package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.models.TranslationRequest
import com.translator.domain.usecases.translation.TranslateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translateUseCase: TranslateUseCase,
): ViewModel() {

    private val _editTextContents = MutableLiveData("")
    val editTextContents: LiveData<String> get() = _editTextContents

    private val _translationResult = MutableLiveData("")
    val translationResult: LiveData<String> get() = _translationResult

    private val _errorState = MutableLiveData(false)
    val errorState: LiveData<Boolean> get() = _errorState

    fun translate(word: String, onTranslated: (String) -> Unit) {
        viewModelScope.launch {
            if (word.isNotEmpty()) {
                try {
                    _translationResult.value = translateUseCase(TranslationRequest(word)).result

                    onTranslated(_translationResult.value.toString())
                } catch (_: Exception) {
                    _errorState.value = true
                }
            }
        }
    }
    fun resetErrorState() {
        _errorState.value = false
    }
    fun updateEditTextContents(newContents: String) {
        _editTextContents.value = newContents
    }
}