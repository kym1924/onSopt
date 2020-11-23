package com.kimym.onsopt.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _autoLogin = MutableLiveData<Boolean>(false)
    val autoLogin : LiveData<Boolean>
        get() = _autoLogin

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin : LiveData<Boolean>
        get() = _isLogin
}
