package com.kimym.onsopt.ui.signin

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    val id = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _autoLogin = MutableLiveData<Boolean>(false)
    val autoLogin : LiveData<Boolean>
        get() = _autoLogin

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid : LiveData<Boolean>
        get() = _isValid

    private lateinit var sharedPref : SharedPreferences
    private lateinit var sharedEdit : SharedPreferences.Editor

    fun setText(){
        id.value = sharedPref.getString("id", "")
        password.value = sharedPref.getString("password", "")
    }

    fun autoLogin(){
        if(id.value == sharedPref.getString("id", "") && id.value != ""
            && password.value == sharedPref.getString("password", "") && password.value != "")
            _autoLogin.value = true
    }

    fun getSharedPref(sharedPreferences : SharedPreferences) {
        sharedPref = sharedPreferences
        sharedEdit = sharedPref.edit()
    }

    fun putSharedPref(){
        sharedEdit.putString("id", id.value)
        sharedEdit.putString("password", password.value)
        sharedEdit.apply()
    }

    fun validation(){
        if(!id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) _isValid.value = true
    }
}