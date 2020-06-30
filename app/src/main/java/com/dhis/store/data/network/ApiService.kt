package com.dhis.store.data.network

import com.dhis.store.data.network.model.ApiDhisAppModel
import retrofit2.http.GET

interface ApiService {

    @GET("/apps")
    suspend fun getApps(): List<ApiDhisAppModel>
}
