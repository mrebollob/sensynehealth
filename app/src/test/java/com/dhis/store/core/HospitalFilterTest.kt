package com.dhis.store.core

import com.dhis.store.core.entity.Sector
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

        filteredApps.forEach {
            TestCase.assertTrue(it.organisationName.contains(testName))
        }
    }

    @Test
    fun `When filter hospitals by NHS, the list contains the only NHS hospitals`() {
        val filter = HospitalFilter(showNHS = true)

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        filteredApps.forEach {
            TestCase.assertEquals(Sector.NHS, it.sector)
        }
    }

    @Test
    fun `When filter hospitals by NHS and hospitals name, the list contains the only NHS hospitals with this name`() {
        val testName = "1"
        val filter = HospitalFilter(
            name = testName,
            showNHS = true
        )

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        filteredApps.forEach {
            TestCase.assertTrue(it.organisationName.contains(testName))
            TestCase.assertEquals(Sector.NHS, it.sector)
        }
    }
}