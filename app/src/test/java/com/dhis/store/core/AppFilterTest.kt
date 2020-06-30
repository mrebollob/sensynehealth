package com.dhis.store.core

import com.dhis.store.utils.TestDataProvider
import com.dhis.store.core.entity.AppFilter
import junit.framework.TestCase
import org.junit.Test
import java.util.*

class AppFilterTest {

    private val testApps = TestDataProvider.getTestApps()

    @Test
    fun `When filter apps by author, the list contains the only apps with this author`() {
        val testAuthor = "dev"
        val filter = AppFilter(author = testAuthor)

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        TestCase.assertEquals(2, filteredApps.size)

        filteredApps.forEach {
            TestCase.assertTrue(it.author.contains(testAuthor))
        }
    }

    @Test
    fun `When filter apps by maxSize, the list contains the only apps with less size`() {
        val testMaxSize = 26
        val filter = AppFilter(maxSize = testMaxSize)

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        filteredApps.forEach {
            TestCase.assertTrue(testMaxSize >= it.sizeInMB)
        }
    }

    @Test
    fun `When filter apps by publishDate, the list contains the only apps published in the date range`() {
        val startDate = Date(10)
        val endDate = Date(20)
        val filter = AppFilter(startDate = startDate, endDate = endDate)

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        filteredApps.forEach {
            TestCase.assertTrue(startDate.time < it.publishDate.time)
            TestCase.assertTrue(endDate.time > it.publishDate.time)
        }
    }

    @Test
    fun `When filter apps by multiple filters, the list contains the only the correct items`() {
        val startDate = Date(10)
        val endDate = Date(20)
        val testMaxSize = 20
        val testAuthor = "dev"
        val filter = AppFilter(
            author = testAuthor,
            maxSize = testMaxSize,
            startDate = startDate,
            endDate = endDate
        )

        val filteredApps = testApps.filter {
            filter.filter(it)
        }

        filteredApps.forEach {
            TestCase.assertTrue(it.author.contains(testAuthor))
            TestCase.assertTrue(testMaxSize > it.sizeInMB)
            TestCase.assertTrue(startDate.time < it.publishDate.time)
            TestCase.assertTrue(endDate.time > it.publishDate.time)
        }
    }
}