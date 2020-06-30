package com.dhis.store.core.entity

import java.util.*

data class DhisApp(
        val id: Int,
        val title: String,
        val description: String,
        val author: String,
        val publishDate: Date,
        val version: String,
        val rating: Float,
        val sizeInMB: Int
)
