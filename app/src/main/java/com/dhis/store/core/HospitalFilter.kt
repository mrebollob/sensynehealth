package com.dhis.store.core

import com.dhis.store.core.entity.Hospital
import java.util.*

data class HospitalFilter(
    val author: String = "",
    val maxSize: Int? = null,
    val startDate: Date? = null,
    val endDate: Date? = null
) {

    fun filter(app: Hospital): Boolean {
        return matchAuthor(app)
    }

    private fun matchAuthor(app: Hospital): Boolean =
        author.isEmpty()
}
