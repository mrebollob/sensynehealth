package com.dhis.store.core.entity

import java.util.*

data class AppFilter(
    val author: String = "",
    val maxSize: Int? = null,
    val startDate: Date? = null,
    val endDate: Date? = null
) {

    fun filter(app: DhisApp): Boolean {
        return matchAuthor(app) &&
                matchSize(app) &&
                matchDate(app)
    }

    private fun matchAuthor(app: DhisApp): Boolean =
        author.isEmpty() || app.author.contains(author)

    private fun matchSize(app: DhisApp): Boolean = maxSize == null || app.sizeInMB <= maxSize

    private fun matchDate(app: DhisApp): Boolean = (startDate == null || endDate == null) ||
            app.publishDate.time in startDate.time..endDate.time
}
