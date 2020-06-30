package com.dhis.store.core

import com.dhis.store.core.entity.AppComment
import com.dhis.store.core.entity.DhisApp
import com.dhis.store.core.entity.FilterType
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun getFilters(): Flow<List<FilterType>>

    suspend fun getApps(): Flow<List<DhisApp>>

    suspend fun getApp(id: Int): Flow<DhisApp>

    suspend fun getCommentsForApp(appId: Int): Flow<List<AppComment>>
}
