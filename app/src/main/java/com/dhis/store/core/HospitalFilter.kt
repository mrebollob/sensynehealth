package com.dhis.store.core

import com.dhis.store.core.entity.Hospital
import com.dhis.store.core.entity.Sector

data class HospitalFilter(
    val name: String = "",
    val showNHS: Boolean = false
) {

    fun filter(hospital: Hospital): Boolean {
        return matchName(hospital) &&
                matchNHS(hospital)
    }

    private fun matchName(hospital: Hospital): Boolean =
        name.isEmpty() || hospital.organisationName.toLowerCase().contains(name.toLowerCase())

    private fun matchNHS(hospital: Hospital): Boolean = !showNHS || hospital.sector == Sector.NHS
}
