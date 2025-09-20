package com.translator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel: ViewModel() {
    private val _navigateToTranslate = MutableLiveData<Boolean>()
    val navigateToTranslate: LiveData<Boolean> get() = _navigateToTranslate

    private val _navigateToFavorites = MutableLiveData<Boolean>()
    val navigateToFavourites: LiveData<Boolean> get() = _navigateToFavorites

    fun goToFavorites() {
        _navigateToFavorites.value = true
    }

    fun goToTranslate() {
        _navigateToTranslate.value = true
    }

    // После обработки сбрасываем событие
    fun doneNavigatingToFavorites() {
        _navigateToFavorites.value = false
    }

    fun doneNavigatingToTranslate() {
        _navigateToTranslate.value = false
    }
}