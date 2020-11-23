package com.kimym.onsopt.ui.mypage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {

    private lateinit var sharedPref : SharedPreferences
    private lateinit var sharedEdit : SharedPreferences.Editor

    private val _logout = MutableLiveData<Boolean>(false)
    val logout : LiveData<Boolean>
        get() = _logout

    fun init(sharedPreferences : SharedPreferences) {
        _logout.value = false
        sharedPref = sharedPreferences
        sharedEdit = sharedPref.edit()
        sharedEdit.apply()
    }

    fun logout() {
        sharedEdit.clear()
        sharedEdit.apply()
        _logout.value = true
    }
}