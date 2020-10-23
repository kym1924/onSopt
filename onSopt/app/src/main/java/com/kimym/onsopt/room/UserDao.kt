package com.kimym.onsopt.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from userDb")
    fun getAll() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)
}