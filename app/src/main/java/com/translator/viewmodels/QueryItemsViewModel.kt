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
import com.translator.domain.usecases.translationitems.history.AddToHistoryUseCase
import com.translator.domain.usecases.translationitems.history.ClearHistoryUseCase
import com.translator.domain.usecases.translationitems.history.GetHistoryUseCase
import com.translator.domain.usecases.translationitems.history.RemoveFromHistoryUseCase
import com.translator.domain.usecases.translationitems.history.UpdateHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueryItemsViewModel @Inject constructor(
    private val addToHistoryUseCase: AddToHistoryUseCase,
    private val updateHistoryUseCase: UpdateHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase,
    private val removeFromHistoryUseCase: RemoveFromHistoryUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
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
            _historyItems.value = addToHistoryUseCase(
                QueryItem(
                    originalWord = word,
                    translatedWord = translation)
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

    fun removeHistoryItem(queryItem: QueryItem) {
        viewModelScope.launch {
            _historyItems.value = removeFromHistoryUseCase(queryItem)
        }
    }

    fun onChangeFavorites(item: QueryItem) {
        viewModelScope.launch {
            val toggled = item.toggleFavorite() as QueryItem

            _historyItems.value = updateHistoryUseCase(toggled)
            if (toggled.isFavorite) {
                _favoritesItems.value = addToFavoritesUseCase(toggled)
            } else {
                _favoritesItems.value = removeFromFavoritesUseCase(toggled)
            }

        }
    }

    fun uncheckAndClearFavorites() {
        viewModelScope.launch {
            _favoritesItems.value?.forEach {
                _historyItems.value = updateHistoryUseCase(it.toggleFavorite())
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