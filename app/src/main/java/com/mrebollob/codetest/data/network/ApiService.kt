package com.mrebollob.codetest.data.network

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {

    @GET("/data/foi/Hospital.csv")
    suspend fun getHospitals(): ResponseBody
}
