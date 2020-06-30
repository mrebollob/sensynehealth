package com.dhis.store.presentation.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhis.store.core.SensyneRepository

class ListViewModelFactory(
    private val repository: SensyneRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ListViewModel(repository) as T
}
