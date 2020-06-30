package com.dhis.store.data.network.model

import com.dhis.store.core.entity.AppComment

data class ApiAppCommentModel(
    val id: Int,
    val appId: Int,
    val user: String,
    val date: String,
    val content: String
) {
    fun toDomainEntity(): AppComment {
        return AppComment(
            id = id,
            appId = appId,
            user = user,
            date = DateParse.parseDate(date),
            content = content
        )
    }
}
