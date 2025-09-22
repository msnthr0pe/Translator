package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.usecases.AddToItemsUseCase
import com.translator.domain.usecases.ClearItemsUseCase
import com.translator.domain.usecases.GetItemsUseCase
import com.translator.domain.usecases.RemoveFromItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val addToHistoryUseCase: AddToItemsUseCase,
    private val clearHistoryUseCase: ClearItemsUseCase,
    private val removeFromHistoryUseCase: RemoveFromItemsUseCase,
    private val getHistoryUseCase: GetItemsUseCase,
): ViewModel() {

    private val _historyItems = MutableLiveData<List<Item>>(emptyList<HistoryItem>())
    val historyItems: LiveData<List<Item>> get() = _historyItems

    fun addToHistory(word: String, translation: String) {
        viewModelScope.launch {
            _historyItems.value = addToHistoryUseCase(
                CompleteTranslation(word,
                    translation)
            )
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            _historyItems.value = getHistoryUseCase()
        }
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