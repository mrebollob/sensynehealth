package com.mrebollob.codetest.core

import com.mrebollob.codetest.core.entity.Hospital
import kotlinx.coroutines.flow.Flow

interface SensyneRepository {

    suspend fun getHospitals(): Flow<List<Hospital>>

    suspend fun getHospital(id: Int): Flow<Hospital>
}
