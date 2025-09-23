package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.StorageType
import com.translator.domain.models.CompleteTranslation
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.usecases.translationitems.AddToItemsUseCase
import com.translator.domain.usecases.translationitems.ClearItemsUseCase
import com.translator.domain.usecases.translationitems.GetItemsUseCase
import com.translator.domain.usecases.translationitems.RemoveFromItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryItemsViewModel @Inject constructor(
    private val addToHistoryUseCase: AddToItemsUseCase,
    private val clearHistoryUseCase: ClearItemsUseCase,
    private val removeFromHistoryUseCase: RemoveFromItemsUseCase,
    private val getHistoryUseCase: GetItemsUseCase,
): ViewModel() {

    private val _historyItems = MutableLiveData<List<Item>>(emptyList<HistoryItem>())
    val historyItems: LiveData<List<Item>> get() = _historyItems
    private val storageType: StorageType = StorageType.HISTORY


    fun addToHistory(word: String, translation: String) {
        viewModelScope.launch {
            _historyItems.value = addToHistoryUseCase(
                CompleteTranslation(word,
                    translation), storageType
            )
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            _historyItems.value = getHistoryUseCase(storageType)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            _historyItems.value = clearHistoryUseCase(storageType)
        }
    }

    fun removeHistoryItem(historyItem: HistoryItem) {
        viewModelScope.launch {
            _historyItems.value = removeFromHistoryUseCase(historyItem, storageType)
        }
    }
}