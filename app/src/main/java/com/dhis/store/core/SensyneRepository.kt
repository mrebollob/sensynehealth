package com.dhis.store.core

import com.dhis.store.core.entity.Hospital
import kotlinx.coroutines.flow.Flow

interface SensyneRepository {

    suspend fun getHospitals(): Flow<List<Hospital>>

    suspend fun getHospital(id: Int): Flow<Hospital>
}
