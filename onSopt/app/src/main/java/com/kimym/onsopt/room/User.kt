package com.example.sopt27.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

@Parcelize
@Entity(tableName = "userDb")
data class User (
    @PrimaryKey(autoGenerate = true)
    val idx : Int,
    val name : String,
    val id : String,
    val password : String,
    val part : String = "android",
    val date : String = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
) : Parcelable