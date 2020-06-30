package com.mrebollob.codetest.presentation.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrebollob.codetest.core.SensyneRepository

class ListViewModelFactory(
    private val repository: SensyneRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ListViewModel(repository) as T
}
