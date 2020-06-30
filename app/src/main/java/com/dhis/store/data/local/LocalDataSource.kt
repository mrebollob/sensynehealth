package com.dhis.store.data.local

import android.content.Context
import android.content.SharedPreferences
import com.dhis.store.core.entity.FilterType
import com.dhis.store.data.local.dao.DhisAppDao
import com.dhis.store.data.local.model.DbDhisAppModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val FILTERS_KEY = "FILTERS_KEY"
private const val STORE_PREFERENCES = "STORE_PREFERENCES"

class LocalDataSource(
    private val dhisAppDao: DhisAppDao,
    private val sharedPref: SharedPreferences
) {

    fun getFilters(): Flow<List<FilterType>> {
        return flow {
            val filterStrings = sharedPref.getString(FILTERS_KEY, "") ?: ""
            emit(filterStrings
                .replace(" ", "")
                .split(",")
                .filter { it.isNotEmpty() }
                .map { FilterType.valueOf(it) })
        }
    }

    fun insertFilters(filterTypes: List<FilterType>) {
        with(sharedPref.edit()) {
            putString(FILTERS_KEY, filterTypes.joinToString { it.name })
            commit()
        }
    }

    fun getApps(): Flow<List<DbDhisAppModel>> = dhisAppDao.getApps()

    fun getApp(id: Int): Flow<DbDhisAppModel> = dhisAppDao.getApp(id)

    suspend fun insertApps(apps: List<DbDhisAppModel>) {
        dhisAppDao.nukeTable()
        dhisAppDao.insertAll(apps)
    }

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: buildInstance(context).also {
                    instance = it
                }
            }

        private fun buildInstance(context: Context): LocalDataSource {
            val dataBase = StoreDatabase.getInstance(context.applicationContext)
            return LocalDataSource(
                dataBase.dhisAppDao(),
                context.getSharedPreferences(STORE_PREFERENCES, Context.MODE_PRIVATE)
            )
        }
    }
}
