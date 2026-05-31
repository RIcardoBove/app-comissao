package com.example.comisso.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.comisso.data.local.converter.Converters
import com.example.comisso.data.local.dao.CommissionDao
import com.example.comisso.data.local.entity.CommissionEntity

@Database(
    entities = [CommissionEntity::class],
    version = 1
)

@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun commissionDao(): CommissionDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database").build()

                INSTANCE = instance

                instance
            }
        }
    }
}
