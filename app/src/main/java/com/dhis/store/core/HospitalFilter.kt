package com.dhis.store.core

import com.dhis.store.core.entity.Hospital

data class HospitalFilter(
    val name: String = ""
) {

    fun filter(app: Hospital): Boolean {
        return matchAuthor(app)
    }

    private fun matchAuthor(app: Hospital): Boolean =
        name.isEmpty() || app.organisationName.toLowerCase().contains(name.toLowerCase())
}
