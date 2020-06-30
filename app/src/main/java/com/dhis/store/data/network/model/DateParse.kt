package com.dhis.store.data.network.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateParse {

    fun parseDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            format.parse(date)
        } catch (e: ParseException) {
            Date(0)
        }
    }
}
