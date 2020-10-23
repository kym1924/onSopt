package com.kimym.onsopt.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from userDb")
    fun getAll() : LiveData<List<User>>

    @Query("SELECT MAX(idx) from userDb")
    fun maxIdx() : Int

    @Query("UPDATE userDb SET idx = :to WHERE idx = :from")
    fun changeIdx(from : Int, to : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)
}