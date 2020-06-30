package com.dhis.store.data

import com.dhis.store.utils.TestDataProvider
import com.dhis.store.data.local.LocalDataSource
import com.dhis.store.data.network.ApiService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class StoreRepositoryTest {

    private val testApps = TestDataProvider.getTestApiApps()

    private val apiService: ApiService = mock()
    private val localDataSource: LocalDataSource = mock()
    private val storeRepository = StoreRepositoryImp(apiService, localDataSource)

    @ExperimentalCoroutinesApi
    @Test
    fun `When get apps from repository, get apps is called in api service and local datasource`() =
        runBlockingTest {
            whenever(apiService.getApps()).thenReturn(testApps)

            storeRepository.getApps()

            verify(apiService).getApps()
            verify(localDataSource).getApps()
        }
}