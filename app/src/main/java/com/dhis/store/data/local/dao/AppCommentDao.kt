package com.dhis.store.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhis.store.data.local.model.DbAppCommentsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppCommentDao {
    @Query("SELECT * FROM comments WHERE appId = :appId ORDER BY date DESC")
    fun getCommentsForApp(appId: Int): Flow<List<DbAppCommentsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<DbAppCommentsModel>)

    @Query("DELETE FROM comments")
    suspend fun nukeTable()
}
