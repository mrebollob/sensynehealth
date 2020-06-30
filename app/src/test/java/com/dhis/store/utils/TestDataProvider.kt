package com.dhis.store.utils

import com.dhis.store.core.entity.Hospital
import com.dhis.store.data.network.model.ApiDhisAppModel
import java.util.*

object TestDataProvider {

    fun getTestApiApps(): List<ApiDhisAppModel> {

        return listOf(
            ApiDhisAppModel(
                id = 0,
                title = "App 0",
                description = "App 0",
                author = "dev",
                publish_date = "2020-01-01",
                version = "1",
                rating = "3.2",
                size = "16M"
            ),
            ApiDhisAppModel(
                id = 1,
                title = "App 1",
                description = "App 1",
                author = "author1",
                publish_date = "2019-04-01",
                version = "1",
                rating = "3.2",
                size = "32M"
            ),
            ApiDhisAppModel(
                id = 2,
                title = "App 2",
                description = "App 2",
                author = "dev",
                publish_date = "2019-10-15",
                version = "1",
                rating = "3.2",
                size = "26M"
            ),
            ApiDhisAppModel(
                id = 3,
                title = "App 3",
                description = "App 3",
                author = "author2",
                publish_date = "2018-06-021",
                version = "1",
                rating = "3.2",
                size = "15M"
            )
        )
    }

    fun getTestApps(): List<Hospital> {

        return listOf(
            Hospital(
                id = 0,
                title = "App 0",
                description = "App 0",
                author = "dev",
                publishDate = Date(5),
                version = "1",
                rating = 3.2f,
                sizeInMB = 16
            ),
            Hospital(
                id = 1,
                title = "App 1",
                description = "App 1",
                author = "author1",
                publishDate = Date(34),
                version = "1",
                rating = 3.2f,
                sizeInMB = 32
            ),
            Hospital(
                id = 2,
                title = "App 2",
                description = "App 2",
                author = "dev",
                publishDate = Date(16),
                version = "1",
                rating = 3.2f,
                sizeInMB = 26
            ),
            Hospital(
                id = 3,
                title = "App 3",
                description = "App 3",
                author = "author2",
                publishDate = Date(11),
                version = "1",
                rating = 3.2f,
                sizeInMB = 15
            )
        )
    }
}