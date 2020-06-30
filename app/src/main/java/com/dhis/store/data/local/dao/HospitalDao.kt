package com.dhis.store.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhis.store.data.local.model.DbHospitalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {
    @Query("SELECT * FROM hospitals")
    fun getHospitals(): Flow<List<DbHospitalModel>>

    @Query("SELECT * FROM hospitals WHERE organisationId = :id")
    fun getHospital(id: Int): Flow<DbHospitalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apps: List<DbHospitalModel>)

    @Query("DELETE FROM hospitals")
    suspend fun nukeTable()
}
