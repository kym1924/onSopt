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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateIdx(users : List<User>)

    @Query("SELECT * from userDb WHERE id = :id")
    fun getMy(id : String) : User

    @Query("SELECT * from userDb ORDER BY idx DESC LIMIT 1")
    fun fromSignUp() : User

    @Query("SELECT password from userDb WHERE id = :id")
    fun login(id : String) : String
}