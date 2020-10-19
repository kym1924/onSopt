package com.example.sopt27.signin

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel  : ViewModel() {

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val autoLogin = MutableLiveData<Boolean>()
    val isValid = MutableLiveData<Boolean>()

    lateinit var sharedPref : SharedPreferences
    lateinit var sharedEdit : SharedPreferences.Editor

    init{
        id.value = ""
        password.value = ""
        autoLogin.value = false
        isValid.value = false
    }

    fun setText(){
        id.value = sharedPref.getString("id", "")
        password.value = sharedPref.getString("password", "")
    }

    fun autoLogin(){
        if(id.value == sharedPref.getString("id", "") && id.value != ""
            && password.value == sharedPref.getString("password", "") && password.value != "")
            autoLogin.value = true
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
        if(!id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) isValid.value = true
    }
}