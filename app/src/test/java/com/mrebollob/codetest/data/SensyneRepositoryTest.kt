package com.mrebollob.codetest.data

import com.mrebollob.codetest.data.local.LocalDataSource
import com.mrebollob.codetest.data.network.ApiService
import com.mrebollob.codetest.data.network.mapper.HospitalsFileMapper
import com.mrebollob.codetest.utils.TestDataProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class SensyneRepositoryTest {

    private val testHospitals = TestDataProvider.getTestApiHospitals()

    private val apiService: ApiService = mock()
    private val localDataSource: LocalDataSource = mock()
    private val hospitalsFileMapper: HospitalsFileMapper = mock()
    private val storeRepository =
        SensyneRepositoryImp(apiService, localDataSource, hospitalsFileMapper)

    @ExperimentalCoroutinesApi
    @Test
    fun `When get hospitals from repository, getHospitals is called in api service and local datasource`() =
        runBlockingTest {
            whenever(apiService.getHospitals()).thenReturn(mock())
            whenever(hospitalsFileMapper.map(any())).thenReturn(testHospitals)

            val hospitals = storeRepository.getHospitals()

            verify(apiService).getHospitals()
            verify(localDataSource).getHospitals()
        }
}