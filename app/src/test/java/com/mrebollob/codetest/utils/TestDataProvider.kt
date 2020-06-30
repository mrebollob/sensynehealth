package com.mrebollob.codetest.utils

import com.mrebollob.codetest.core.entity.Hospital
import com.mrebollob.codetest.core.entity.Sector
import com.mrebollob.codetest.data.network.model.ApiHospitalModel

object TestDataProvider {

    fun getTestApiHospitals(): List<ApiHospitalModel> = listOf(
        getTestApiHospital(0),
        getTestApiHospital(1),
        getTestApiHospital(2)
    )

    fun getTestHospitals(): List<Hospital> = listOf(
        getTestHospital(0),
        getTestHospital(1),
        getTestHospital(2)
    )

    private fun getTestApiHospital(seed: Int) = ApiHospitalModel(
        seed,
        "code_$seed",
        "type_$seed",
        "subtype_$seed",
        "sector_$seed",
        "status_$seed",
        false,
        "name_$seed",
        "address_$seed",
        "",
        "",
        "country_$seed",
        "postcode_$seed",
        "",
        "",
        "",
        "",
        "",
        "",
        "email_$seed",
        "web_$seed",
        ""
    )

    private fun getTestHospital(seed: Int) = Hospital(
        seed,
        "code_$seed",
        "type_$seed",
        "subtype_$seed",
        if (seed % 2 == 0) Sector.NHS else Sector.INDEPENDENT,
        "status_$seed",
        false,
        "name_$seed",
        "address_$seed",
        "",
        "",
        "country_$seed",
        "postcode_$seed",
        "",
        "",
        "",
        "",
        "",
        "",
        "email_$seed",
        "web_$seed",
        ""
    )
}