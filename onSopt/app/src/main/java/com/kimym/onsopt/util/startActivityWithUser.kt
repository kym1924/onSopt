package com.kimym.onsopt.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.kimym.onsopt.room.User

inline fun <reified T : Activity> Context.startActivityWithUser(user : User){
    val intent = Intent(this, T ::class.java)
    intent.putExtra("user", user)
    startActivity(intent)
}