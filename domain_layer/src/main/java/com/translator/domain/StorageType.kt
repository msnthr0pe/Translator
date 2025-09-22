package com.translator.domain

sealed class StorageType {
    object History : StorageType()
    object Favorites : StorageType()
}