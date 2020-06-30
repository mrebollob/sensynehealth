package com.dhis.store.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhis.store.data.local.model.DbDhisAppModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DhisAppDao {
    @Query("SELECT * FROM apps ORDER BY rating DESC")
    fun getApps(): Flow<List<DbDhisAppModel>>

    @Query("SELECT * FROM apps WHERE id = :id")
    fun getApp(id: Int): Flow<DbDhisAppModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apps: List<DbDhisAppModel>)

    @Query("DELETE FROM apps")
    suspend fun nukeTable()
}
