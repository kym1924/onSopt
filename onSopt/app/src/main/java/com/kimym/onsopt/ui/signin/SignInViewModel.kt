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

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid : LiveData<Boolean>
        get() = _isValid

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

    fun putSharedPref() {
        sharedEdit.putString("id", id.value)
        sharedEdit.putString("password", password.value)
        sharedEdit.apply()
    }

    fun validation() {
        if(!id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) _isValid.value = true
    }

    fun fromSignUp() = viewModelScope.launch(Dispatchers.IO) {
        _fromSignUp.postValue(userDao.fromSignUp())
    }
}
