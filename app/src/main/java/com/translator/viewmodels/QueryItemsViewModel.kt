package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.models.QueryItem
import com.translator.domain.models.Item
import com.translator.domain.usecases.translationitems.favorites.AddToFavoritesUseCase
import com.translator.domain.usecases.translationitems.favorites.ClearFavoritesUseCase
import com.translator.domain.usecases.translationitems.favorites.GetFavoritesUseCase
import com.translator.domain.usecases.translationitems.favorites.RemoveFromFavoritesUseCase
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
class QueryItemsViewModel @Inject constructor(
    private val addToHistoryUseCase: AddToItemsUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val checkIfItemFavoriteUseCase: CheckIfItemFavoriteUseCase,
    private val clearHistoryUseCase: ClearItemsUseCase,
    private val removeFromHistoryUseCase: RemoveFromItemsUseCase,
    private val getHistoryUseCase: GetItemsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val getFavoriteUseCase: GetFavoritesUseCase,
    private val clearFavoritesUseCase: ClearFavoritesUseCase,
    ): ViewModel() {

    private val _historyItems = MutableLiveData<List<Item>>(emptyList<QueryItem>())
    val historyItems: LiveData<List<Item>> get() = _historyItems
    private val _favoritesItems = MutableLiveData<List<Item>>(emptyList<QueryItem>())
    val favoritesItems: LiveData<List<Item>> get() = _favoritesItems
    fun addToHistory(word: String, translation: String) {
        viewModelScope.launch {
            var newItem = QueryItem(originalWord = word,
                translatedWord = translation)
            if (checkIfItemFavoriteUseCase(newItem)) {
                newItem = newItem.toggleFavorite() as QueryItem
            }
            if (checkIfFavorite(newItem)) {
                _historyItems.value = addToHistoryUseCase(
                    newItem.setFavorite(true)
                )
            } else {
                _historyItems.value = addToHistoryUseCase(
                    newItem
                )
            }
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

    fun removeHistoryItem(queryItem: QueryItem) {
        viewModelScope.launch {
            _historyItems.value = removeFromHistoryUseCase(queryItem)
        }
    }

    fun onChangeFavorites(item: QueryItem) {
        viewModelScope.launch {
            val toggled = item.toggleFavorite() as QueryItem

            _historyItems.value = updateItemUseCase(toggled)
            if (toggled.isFavorite) {
                _favoritesItems.value = addToFavoritesUseCase(toggled)
            } else {
                _favoritesItems.value = removeFromFavoritesUseCase(toggled)
            }

        }
    }

    private suspend fun checkIfFavorite(queryItem: QueryItem): Boolean {
        val favorites = getFavoriteUseCase()
        return favorites.any { it.originalWord == queryItem.originalWord }
    }

    fun uncheckAndClearFavorites() {
        viewModelScope.launch {
            _favoritesItems.value?.forEach {
                _historyItems.value = updateItemUseCase(it.toggleFavorite())
            }
            _favoritesItems.value = clearFavoritesUseCase()
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favoritesItems.value = getFavoriteUseCase()
        }
    }
}