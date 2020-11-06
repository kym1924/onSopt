package com.kimym.onsopt.ui.mypage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _logout = MutableLiveData<Boolean>(false)
    val logout : LiveData<Boolean>
        get() = _logout

    fun init(sharedPreferences : SharedPreferences, dao : UserDao) {
        _logout.value = false
        sharedPref = sharedPreferences
        sharedEdit = sharedPref.edit()
        userDao = dao
    }

    fun getMy() = viewModelScope.launch(Dispatchers.IO) {
        my = userDao.getMy(sharedPref.getString("id", "")!!)
    }

    fun logout() {
        sharedEdit.clear()
        sharedEdit.apply()
        _logout.value = true
    }
}