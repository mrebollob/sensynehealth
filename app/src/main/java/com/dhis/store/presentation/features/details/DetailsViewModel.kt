package com.dhis.store.presentation.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dhis.store.core.StoreRepository
import com.dhis.store.core.entity.AppComment
import com.dhis.store.core.entity.DhisApp
import kotlinx.coroutines.flow.collect

class DetailsViewModel(
    private val storeRepository: StoreRepository,
    private val appId: Int
) : ViewModel() {

    val app: LiveData<DhisApp> = liveData {
        storeRepository.getApp(appId)
            .collect { apps -> emit(apps) }
    }

    val comments: LiveData<List<AppComment>> = liveData {
        storeRepository.getCommentsForApp(appId)
            .collect { comments -> emit(comments) }
    }
}
