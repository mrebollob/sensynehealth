package com.dhis.store.core

import com.dhis.store.core.entity.DhisApp
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun getApps(): Flow<List<DhisApp>>

    suspend fun getApp(id: Int): Flow<DhisApp>
}
