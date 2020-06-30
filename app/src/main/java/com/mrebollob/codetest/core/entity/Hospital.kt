package com.mrebollob.codetest.core.entity

data class Hospital(
    val organisationId: Int,
    val organisationCode: String,
    val organisationType: String,
    val subType: String,
    val sector: Sector,
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
)
