package com.kimym.onsopt.ui.fragment

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    lateinit var my : User

    private lateinit var userDao : UserDao
    private lateinit var sharedPref : SharedPreferences
    private lateinit var sharedEdit : SharedPreferences.Editor

    fun init(sharedPreferences : SharedPreferences, dao : UserDao) {
        sharedPref = sharedPreferences
        sharedEdit = sharedPref.edit()
        userDao = dao
    }

    fun getMy() = viewModelScope.launch(Dispatchers.IO) {
        my = userDao.getMy(sharedPref.getString("id", "")!!)
    }
}