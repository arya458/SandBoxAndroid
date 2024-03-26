package com.sandbox.sandboxMessenger.di.module

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sandbox.sandboxMessenger.data.dao.MessengerDao
import com.sandbox.sandboxMessenger.data.response.MessengerResponse

@Database(
        entities = [MessengerResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MessengerDao(): MessengerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
                context: Context
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "SandBoxMessenger"
                )
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}