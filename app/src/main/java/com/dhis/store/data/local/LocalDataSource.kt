package com.dhis.store.data.local

import android.content.Context
import android.content.SharedPreferences
import com.dhis.store.core.entity.FilterType
import com.dhis.store.data.local.dao.HospitalDao
import com.dhis.store.data.local.model.DbHospitalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val FILTERS_KEY = "FILTERS_KEY"
private const val STORE_PREFERENCES = "STORE_PREFERENCES"

class LocalDataSource(
    private val hospitalDao: HospitalDao,
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

    fun getHospitals(): Flow<List<DbHospitalModel>> = hospitalDao.getHospitals()

    fun getHospital(id: Int): Flow<DbHospitalModel> = hospitalDao.getHospital(id)

    suspend fun insertHospitals(hospitals: List<DbHospitalModel>) {
        hospitalDao.nukeTable()
        hospitalDao.insertAll(hospitals)
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
                dataBase.hospitalDao(),
                context.getSharedPreferences(STORE_PREFERENCES, Context.MODE_PRIVATE)
            )
        }
    }
}
