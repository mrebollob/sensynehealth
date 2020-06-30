package com.dhis.store.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhis.store.core.entity.DhisApp
import java.util.*

@Entity(tableName = "apps")
data class DbDhisAppModel(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val publishDate: Date,
    val version: String,
    val rating: Float,
    val sizeInMB: Int
) {
    fun toDomainEntity(): DhisApp {
        return DhisApp(
            id = id,
            title = title,
            description = description,
            author = author,
            publishDate = publishDate,
            version = version,
            rating = rating,
            sizeInMB = sizeInMB
        )
    }
}

fun DhisApp.toDbEntity() = with(this) {
    DbDhisAppModel(
        id = id,
        title = title,
        description = description,
        author = author,
        publishDate = publishDate,
        version = version,
        rating = rating,
        sizeInMB = sizeInMB
    )
}
