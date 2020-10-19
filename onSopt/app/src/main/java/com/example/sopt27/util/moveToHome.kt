package com.example.sopt27.util

import android.content.Context
import android.content.Intent
import com.example.sopt27.home.HomeActivity

fun Context.moveToHome(){
    val intent = Intent(this, HomeActivity::class.java)
    startActivity(intent)
}