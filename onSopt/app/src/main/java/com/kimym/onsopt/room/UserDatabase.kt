package com.kimym.onsopt.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE : UserDatabase? = null

        fun getDatabase(context : Context) : UserDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "userDb"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun userDao() : UserDao
}