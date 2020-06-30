package com.dhis.store.presentation.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dhis.store.core.SensyneRepository
import com.dhis.store.core.entity.Hospital
import kotlinx.coroutines.flow.collect

class DetailsViewModel(
    private val sensyneRepository: SensyneRepository,
    private val organisationId: Int
) : ViewModel() {

    val app: LiveData<Hospital> = liveData {
        sensyneRepository.getHospital(organisationId)
            .collect { apps -> emit(apps) }
    }
}
