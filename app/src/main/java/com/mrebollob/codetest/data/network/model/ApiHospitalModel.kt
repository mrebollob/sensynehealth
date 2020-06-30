package com.mrebollob.codetest.data.network.model

import com.mrebollob.codetest.data.local.model.DbHospitalModel

data class ApiHospitalModel(
    val organisationId: Int,
    val organisationCode: String,
    val organisationType: String,
    val subType: String,
    val sector: String,
    val organisationStatus: String,
    val isPimsManaged: Boolean,
    val organisationName: String,
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val county: String,
    val postcode: String,
    val latitude: String,
    val longitude: String,
    val parentODSCode: String,
    val parentName: String,
    val phone: String,
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
            city = city,
            county = county,
            postcode = postcode,
            latitude = latitude,
            longitude = longitude,
            parentODSCode = parentODSCode,
            parentName = parentName,
            phone = phone,
            email = email,
            website = website,
            fax = fax
        )
    }
}
