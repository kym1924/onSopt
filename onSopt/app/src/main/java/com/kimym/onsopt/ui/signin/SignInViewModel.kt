package com.kimym.onsopt.ui.signin

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    val id = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _autoLogin = MutableLiveData<Boolean>(false)
    val autoLogin : LiveData<Boolean>
        get() = _autoLogin

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin : LiveData<Boolean>
        get() = _isLogin

    private val _fromSignUp = MutableLiveData<User>()
    val fromSignUp : LiveData<User>
        get() = _fromSignUp

    private lateinit var sharedPref : SharedPreferences
    private lateinit var sharedEdit : SharedPreferences.Editor
    private lateinit var userDao : UserDao

    fun init(sharedPreferences : SharedPreferences, dao : UserDao) {
        sharedPref = sharedPreferences
        sharedEdit = sharedPref.edit()
        userDao = dao
        this.autoLogin()
    }

    private fun autoLogin() {
        id.value = sharedPref.getString("id", "")
        password.value = sharedPref.getString("password", "")
        this.autoLoginCheck()
    }

    private fun autoLoginCheck() {
        if(id.value == sharedPref.getString("id", "") && id.value != ""
            && password.value == sharedPref.getString("password", "") && password.value != "")
            _autoLogin.value = true
    }

    fun validation() {
        if(!id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) login()
        else _isLogin.value = false
    }

    private fun login() = viewModelScope.launch(Dispatchers.IO) {
        if(userDao.login(id.value!!) == password.value!!) {
            putSharedPref()
            _isLogin.postValue(true)
        } else _isLogin.postValue(false)
    }

    private fun putSharedPref() {
        sharedEdit.putString("id", id.value)
        sharedEdit.putString("password", password.value)
        sharedEdit.apply()
    }

    fun fromSignUp() = viewModelScope.launch(Dispatchers.IO) {
        _fromSignUp.postValue(userDao.fromSignUp())
    }
}
