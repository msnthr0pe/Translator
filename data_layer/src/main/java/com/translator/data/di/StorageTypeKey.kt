package com.translator.data.di

import com.translator.domain.StorageType
import dagger.MapKey

@MapKey
annotation class StorageTypeKey(val value: StorageType)