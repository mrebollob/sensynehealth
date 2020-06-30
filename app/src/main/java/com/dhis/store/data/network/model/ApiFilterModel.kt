package com.dhis.store.data.network.model

import com.dhis.store.core.entity.FilterType

data class ApiFilterModel(
    val id: Int,
    val type: String
) {
    fun toDomainEntity(): FilterType? {
        return when (type) {
            "size" -> FilterType.SIZE
            "author" -> FilterType.AUTHOR
            "date" -> FilterType.DATE
            else -> null
        }
    }
}
