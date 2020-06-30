package com.dhis.store.data.network.model

import com.dhis.store.core.entity.Hospital

data class ApiDhisAppModel(
    val author: String,
    val description: String,
    val id: Int,
    val publish_date: String,
    val rating: String,
    val size: String,
    val title: String,
    val version: String
) {
    fun toDomainEntity(): Hospital {
        return Hospital(
            id = id,
            title = title,
            description = description,
            author = author,
            publishDate = DateParse.parseDate(publish_date),
            version = version,
            rating = rating.toFloatOrNull() ?: 0f,
            sizeInMB = parseSize(size)
        )
    }

    private fun parseSize(size: String): Int = Integer.parseInt(size.replace("M", ""))
}
