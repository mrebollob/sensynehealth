package com.dhis.store.core

import com.dhis.store.utils.TestDataProvider
import junit.framework.TestCase
import org.junit.Test

class HospitalFilterTest {

    private val testApps = TestDataProvider.getTestHospitals()

    @Test
    fun `When filter hospitals by name, the list contains the only hospitals that contains this name`() {
        val testName = "1"
        val filter = HospitalFilter(name = testName)

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        TestCase.assertEquals(1, filteredApps.size)

        filteredApps.forEach {
            TestCase.assertTrue(it.organisationName.contains(testName))
        }
    }
}