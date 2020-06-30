package com.mrebollob.codetest.presentation.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mrebollob.codetest.core.SensyneRepository
import com.mrebollob.codetest.core.entity.Hospital
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
