package com.translator.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class FavoritesItemsViewModel @Inject constructor(
    private val addToFavoritesUseCase: AddToItemsUseCase,
    private val clearFavoritesUseCase: ClearItemsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromItemsUseCase,
    private val getFavoritesUseCase: GetItemsUseCase,
): ViewModel() {

    private val _favoritesItems = MutableLiveData<List<Item>>(emptyList<HistoryItem>())
    val favoritesItems: LiveData<List<Item>> get() = _favoritesItems


    fun manageFavorites(item: HistoryItem, position: Int, list: List<Item>): List<Item> {

        viewModelScope.launch {
            if (list[position].isFavorite) {
                _favoritesItems.value = addToFavoritesUseCase(
                    CompleteTranslation(
                        item.originalWord,
                        item.translatedWord
                    )
                )
            } else {
                removeFavoritesItem(HistoryItem(item.id, item.originalWord, item.translatedWord))
            }
        }

        val newList = list.toMutableList()
        newList[position] = item.toggleFavorite()
        return newList

    }


    fun loadFavorites() {
        viewModelScope.launch {
            _favoritesItems.value = getFavoritesUseCase()
        }
    }

    fun clearFavorites() {
        viewModelScope.launch {
            _favoritesItems.value = clearFavoritesUseCase()
        }
    }

    fun removeFavoritesItem(historyItem: HistoryItem) {
        viewModelScope.launch {
            _favoritesItems.value = removeFromFavoritesUseCase(historyItem)
        }
    }
}