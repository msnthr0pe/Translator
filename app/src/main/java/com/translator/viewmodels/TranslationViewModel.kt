package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.TranslationRequest
import com.translator.domain.usecases.TranslateUseCase
import com.translator.domain.models.HistoryItem
import com.translator.domain.usecases.AddToHistoryUseCase
import com.translator.domain.usecases.ClearHistoryUseCase
import com.translator.domain.usecases.RemoveFromHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translateUseCase: TranslateUseCase,
    private val addToHistoryUseCase: AddToHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase,
    private val removeFromHistoryUseCase: RemoveFromHistoryUseCase,
): ViewModel() {

    private val _editTextContents = MutableLiveData<String>()
    val editTextContents: LiveData<String> get() = _editTextContents

    private val _translationResult = MutableLiveData<String>()
    val translationResult: LiveData<String> get() = _translationResult

    private val _historyItems = MutableLiveData<List<HistoryItem>>(emptyList())
    val historyItems: LiveData<List<HistoryItem>> get() = _historyItems


    fun translate(word: String) {
        viewModelScope.launch {
            if (word.isNotEmpty()) {
                _translationResult.value = translateUseCase(TranslationRequest(word)).result

                _historyItems.value = addToHistoryUseCase(
                    CompleteTranslation(word,
                    _translationResult.value.toString())
                )
            }
        }
    }
    fun updateEditTextContents(newContents: String) {
        _editTextContents.value = newContents
    }

    fun clearHistory() {
        viewModelScope.launch {
            _historyItems.value = clearHistoryUseCase()
        }
    }

    fun removeHistoryItem(historyItem: HistoryItem) {
        viewModelScope.launch {
            _historyItems.value = removeFromHistoryUseCase(historyItem)
        }
    }
}