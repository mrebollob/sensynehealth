package com.mrebollob.codetest.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrebollob.codetest.core.SensyneRepository

class DetailsViewModelFactory(
    private val repository: SensyneRepository,
    private val appId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DetailsViewModel(repository, appId) as T
}
