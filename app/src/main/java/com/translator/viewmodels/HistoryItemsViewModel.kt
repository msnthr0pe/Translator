package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.models.QueryItem
import com.translator.domain.models.Item
import com.translator.domain.usecases.translationitems.history.AddToItemsUseCase
import com.translator.domain.usecases.translationitems.history.CheckIfItemFavoriteUseCase
import com.translator.domain.usecases.translationitems.history.ClearItemsUseCase
import com.translator.domain.usecases.translationitems.history.GetItemsUseCase
import com.translator.domain.usecases.translationitems.history.RemoveFromItemsUseCase
import com.translator.domain.usecases.translationitems.history.UpdateItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryItemsViewModel @Inject constructor(
    private val addToHistoryUseCase: AddToItemsUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val checkIfItemFavoriteUseCase: CheckIfItemFavoriteUseCase,
    private val clearHistoryUseCase: ClearItemsUseCase,
    private val removeFromHistoryUseCase: RemoveFromItemsUseCase,
    private val getHistoryUseCase: GetItemsUseCase,
): ViewModel() {

    private val _queryItems = MutableLiveData<List<Item>>(emptyList<QueryItem>())
    val historyItems: LiveData<List<Item>> get() = _queryItems
    private val _favoritesItems = MutableLiveData<List<Item>>(emptyList<QueryItem>())
    val favoritesItems: LiveData<List<Item>> get() = _favoritesItems
    fun addToHistory(word: String, translation: String) {
        viewModelScope.launch {
            var newItem = QueryItem(originalWord = word,
                translatedWord = translation)
            if (checkIfItemFavoriteUseCase(newItem)) {
                newItem = newItem.toggleFavorite() as QueryItem
            }
            _queryItems.value = addToHistoryUseCase(
                newItem
            )
        }
    }


    fun loadHistory() {
        viewModelScope.launch {
            _queryItems.value = getHistoryUseCase()
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            _queryItems.value = clearHistoryUseCase()
        }
    }

    fun removeHistoryItem(queryItem: QueryItem) {
        viewModelScope.launch {
            _queryItems.value = removeFromHistoryUseCase(queryItem)
        }
    }

    fun manageFavorites(item: QueryItem) {
        viewModelScope.launch {
            val toggled = item.toggleFavorite() as QueryItem
            _queryItems.value = updateItemUseCase(toggled)
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