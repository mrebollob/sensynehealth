package com.dhis.store.core.entity

import java.util.*

data class AppComment(
    val id: Int,
    val appId: Int,
    val user: String,
    val date: Date,
    val content: String
)
