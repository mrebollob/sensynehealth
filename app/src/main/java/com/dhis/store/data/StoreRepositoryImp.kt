package com.dhis.store.data

import android.content.Context
import android.util.Log
import com.dhis.store.core.StoreRepository
import com.dhis.store.core.entity.AppComment
import com.dhis.store.core.entity.DhisApp
import com.dhis.store.core.entity.FilterType
import com.dhis.store.data.local.LocalDataSource
import com.dhis.store.data.local.model.toDbEntity
import com.dhis.store.data.network.ApiService
import com.dhis.store.di.InjectorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.ConnectException

class StoreRepositoryImp(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : StoreRepository {

    override suspend fun getFilters(): Flow<List<FilterType>> {
        refreshFilters()
        return localDataSource.getFilters()
    }

    override suspend fun getApps(): Flow<List<DhisApp>> {
        refreshDBApps()
        return localDataSource.getApps().map { it.map { dbApp -> dbApp.toDomainEntity() } }
    }

    override suspend fun getApp(id: Int): Flow<DhisApp> =
        localDataSource.getApp(id).map { dbApp -> dbApp.toDomainEntity() }

    override suspend fun getCommentsForApp(appId: Int): Flow<List<AppComment>> {
        refreshDBComments(appId)
        return localDataSource.getCommentsForApp(appId)
            .map { it.map { dbApp -> dbApp.toDomainEntity() } }
    }

    private suspend fun refreshDBApps() = try {
        val apps = apiService.getApps().map { apiModel -> apiModel.toDomainEntity() }
        localDataSource.insertApps(apps.map { it.toDbEntity() })
    } catch (connectException: ConnectException) {
        Log.d("StoreRepositoryImp", "refreshDBApps", connectException)
    }

    private suspend fun refreshDBComments(id: Int) = try {
        val comments =
            apiService.getComments(id.toString()).map { apiModel -> apiModel.toDomainEntity() }
        localDataSource.insertComments(comments.map { it.toDbEntity() })
    } catch (connectException: ConnectException) {
        Log.d("StoreRepositoryImp", "refreshDBComments", connectException)
    }


    private suspend fun refreshFilters() = try {
        val filters =
            apiService.getFilters().mapNotNull { apiModel -> apiModel.toDomainEntity() }
        localDataSource.insertFilters(filters)
    } catch (connectException: ConnectException) {
        Log.d("StoreRepositoryImp", "refreshFilters", connectException)
    }

    companion object {
        @Volatile
        private var instance: StoreRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: buildInstance(context).also {
                    instance = it
                }
            }

        private fun buildInstance(context: Context): StoreRepository {
            return StoreRepositoryImp(
                InjectorUtils.provideApiService(),
                LocalDataSource.getInstance(context)
            )
        }
    }
}
