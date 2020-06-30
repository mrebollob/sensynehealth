package com.dhis.store.data.network.model

import com.dhis.store.data.local.model.DbHospitalModel

data class ApiHospitalModel(
    val organisationId: Int,
    val organisationCode: String,
    val organisationType: String,
    val subType: String,
    val sector: String,
    val organisationStatus: String,
    val isPimsManaged: String,
    val organisationName: String,
    val address1: String,
    val address2: String,
    val address3: String,
    val county: String,
    val postcode: String,
    val latitude: String,
    val longitude: String,
    val parentODSCode: String,
    val parentName: String,
    val email: String,
    val website: String,
    val fax: String
) {
    fun toDbEntity(): DbHospitalModel {
        return DbHospitalModel(
            organisationId = organisationId,
            organisationCode = organisationCode,
            organisationType = organisationType,
            subType = subType,
            sector = sector,
            organisationStatus = organisationStatus,
            isPimsManaged = isPimsManaged,
            organisationName = organisationName,
            address1 = address1,
            address2 = address2,
            address3 = address3,
            county = county,
            postcode = postcode,
            latitude = latitude,
            longitude = longitude,
            parentODSCode = parentODSCode,
            parentName = parentName,
            email = email,
            website = website,
            fax = fax
        )
    }
}
