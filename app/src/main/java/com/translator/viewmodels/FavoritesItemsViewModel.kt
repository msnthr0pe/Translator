package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.translator.domain.StorageType
import com.translator.domain.models.FavoritesItem
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

    private val _favoritesItems = MutableLiveData<List<Item>>(emptyList<FavoritesItem>())
    val favoritesItems: LiveData<List<Item>> get() = _favoritesItems
    private val storageType: StorageType = StorageType.FAVORITES


    fun addToFavorites(historyItem: HistoryItem) {
//        viewModelScope.launch {
//            _favoritesItems.value = addToFavoritesUseCase(
//                CompleteTranslation(word,
//                    translation), storageType
//            )
//        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favoritesItems.value = getFavoritesUseCase(storageType)
        }
    }

    fun clearFavorites() {
        viewModelScope.launch {
            _favoritesItems.value = clearFavoritesUseCase(storageType)
        }
    }

    fun removeHistoryItem(favoritesItem: FavoritesItem) {
        viewModelScope.launch {
            _favoritesItems.value = removeFromFavoritesUseCase(favoritesItem, storageType)
        }
    }
}