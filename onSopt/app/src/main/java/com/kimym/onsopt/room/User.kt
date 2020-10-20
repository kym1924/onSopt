package com.example.sopt27.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDb")
data class User (
    @PrimaryKey(autoGenerate = true)
    val idx : Int,
    val name : String,
    val id : String,
    val password : String
)