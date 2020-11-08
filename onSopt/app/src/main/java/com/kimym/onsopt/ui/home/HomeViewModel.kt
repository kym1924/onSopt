package com.kimym.onsopt.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _myName = MutableLiveData<String>()
    val myName : LiveData<String>
        get() = _myName

    private lateinit var userDao : UserDao
    private lateinit var sharedPref : SharedPreferences
    private lateinit var sharedEdit : SharedPreferences.Editor

    fun init(sharedPreferences : SharedPreferences, dao : UserDao) {
        sharedPref = sharedPreferences
        sharedEdit = sharedPref.edit()
        userDao = dao
    }

    fun getMyName() = viewModelScope.launch(Dispatchers.IO) {
        _myName.postValue(userDao.getMyName(sharedPref.getString("id", "")!!))
    }
}