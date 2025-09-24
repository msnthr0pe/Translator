package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.usecases.translationitems.AddToItemsUseCase
import com.translator.domain.usecases.translationitems.CheckIfItemFavoriteUseCase
import com.translator.domain.usecases.translationitems.ClearItemsUseCase
import com.translator.domain.usecases.translationitems.GetItemsUseCase
import com.translator.domain.usecases.translationitems.RemoveFromItemsUseCase
import com.translator.domain.usecases.translationitems.UpdateItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryItemsViewModel @Inject constructor(
    private val addToHistoryUseCase: AddToItemsUseCase,
    private val updateItemsUseCase: UpdateItemsUseCase,
    private val checkIfItemFavoriteUseCase: CheckIfItemFavoriteUseCase,
    private val clearHistoryUseCase: ClearItemsUseCase,
    private val removeFromHistoryUseCase: RemoveFromItemsUseCase,
    private val getHistoryUseCase: GetItemsUseCase,
): ViewModel() {

    private val _historyItems = MutableLiveData<List<Item>>(emptyList<HistoryItem>())
    val historyItems: LiveData<List<Item>> get() = _historyItems
    private val _favoritesItems = MutableLiveData<List<Item>>(emptyList<HistoryItem>())
    val favoritesItems: LiveData<List<Item>> get() = _favoritesItems
    fun addToHistory(word: String, translation: String) {
        viewModelScope.launch {
            var newItem = HistoryItem(originalWord = word,
                translatedWord = translation)
            if (checkIfItemFavoriteUseCase(newItem)) {
                newItem = newItem.toggleFavorite() as HistoryItem
            }
            _historyItems.value = addToHistoryUseCase(
                newItem
            )
        }
    }

    private fun updateHistory(list: List<Item>) {
        _historyItems.value = list
        viewModelScope.launch {
            _historyItems.value = updateItemsUseCase(list)
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

    fun manageFavorites(item: HistoryItem, position: Int) {

        val list = _historyItems.value

        viewModelScope.launch {
            val newList = list?.toMutableList()
            newList?.set(position, item.toggleFavorite())
            if (newList != null) {
                updateHistory(newList)
            }

        }


    }


//    fun loadFavorites() {
//        viewModelScope.launch {
//            _favoritesItems.value = getFavoritesUseCase()
//        }
//    }
//
//    fun clearFavorites() {
//        viewModelScope.launch {
//            _favoritesItems.value = clearFavoritesUseCase()
//        }
//    }
//
//    fun removeFavoritesItem(historyItem: HistoryItem) {
//        viewModelScope.launch {
//            _favoritesItems.value = removeFromFavoritesUseCase(historyItem)
//        }
//    }
}