package com.dhis.store.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhis.store.core.entity.AppComment
import java.util.*

@Entity(tableName = "comments")
data class DbAppCommentsModel(
    @PrimaryKey val id: Int,
    val appId: Int,
    val user: String,
    val date: Date,
    val content: String
) {
    fun toDomainEntity(): AppComment {
        return AppComment(
            id = id,
            appId = appId,
            user = user,
            date = date,
            content = content
        )
    }
}

fun AppComment.toDbEntity() = with(this) {
    DbAppCommentsModel(
        id = id,
        appId = appId,
        user = user,
        date = date,
        content = content
    )
}
