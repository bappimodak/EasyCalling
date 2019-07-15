package com.example.callingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.callingapp.model.UserDetails

@Database(entities = [UserDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        //having singleton design pattern to have only one db object at any point of time
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "call_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


//.allowMainThreadQueries():-doesn't support database access on the main thread