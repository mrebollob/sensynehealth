package com.dhis.store.data.local

import android.content.Context
import com.dhis.store.data.local.dao.HospitalDao
import com.dhis.store.data.local.model.DbHospitalModel
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val hospitalDao: HospitalDao
) {

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
                dataBase.hospitalDao()
            )
        }
    }
}
