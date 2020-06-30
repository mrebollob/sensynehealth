package com.dhis.store.data.network

import com.dhis.store.data.network.model.ApiAppCommentModel
import com.dhis.store.data.network.model.ApiDhisAppModel
import com.dhis.store.data.network.model.ApiFilterModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/apps")
    suspend fun getApps(): List<ApiDhisAppModel>

    @GET("/apps/{id}/comments")
    suspend fun getComments(@Path("id") token: String): List<ApiAppCommentModel>

    @GET("/filters")
    suspend fun getFilters(): List<ApiFilterModel>
}
