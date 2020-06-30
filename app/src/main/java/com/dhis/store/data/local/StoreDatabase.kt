package com.dhis.store.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dhis.store.data.local.converter.Converters
import com.dhis.store.data.local.dao.AppCommentDao
import com.dhis.store.data.local.dao.DhisAppDao
import com.dhis.store.data.local.model.DbAppCommentsModel
import com.dhis.store.data.local.model.DbDhisAppModel

@Database(
    entities = [DbDhisAppModel::class, DbAppCommentsModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun dhisAppDao(): DhisAppDao
    abstract fun appCommentDao(): AppCommentDao

    companion object {

        @Volatile
        private var instance: StoreDatabase? = null

        fun getInstance(context: Context): StoreDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): StoreDatabase =
            Room.databaseBuilder(context, StoreDatabase::class.java, DATABASE_NAME).build()
    }
}

const val DATABASE_NAME = "store-db"
