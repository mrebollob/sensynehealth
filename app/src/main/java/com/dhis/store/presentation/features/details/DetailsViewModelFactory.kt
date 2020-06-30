package com.dhis.store.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhis.store.core.StoreRepository

class DetailsViewModelFactory(
    private val repository: StoreRepository,
    private val appId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DetailsViewModel(repository, appId) as T
}
