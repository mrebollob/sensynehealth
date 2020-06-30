package com.dhis.store.presentation.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhis.store.core.StoreRepository

class AppListViewModelFactory(
    private val repository: StoreRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = AppListViewModel(repository) as T
}
