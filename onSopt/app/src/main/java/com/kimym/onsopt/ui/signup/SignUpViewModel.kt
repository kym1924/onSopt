package com.kimym.onsopt.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData<String>("")
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val passwordCheck = MutableLiveData<String>("")

    private val _isSamePassword = MutableLiveData<Boolean>(false)
    val isSamePassword : LiveData<Boolean>
        get() = _isSamePassword

    private val _isValid = MutableLiveData<Boolean>()
    val isValid : LiveData<Boolean>
        get() = _isValid

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean>
        get() = _isSuccess

    fun checkPassword() {
        _isSamePassword.value = (password.value == passwordCheck.value && !password.value.isNullOrEmpty())
    }

    fun validation() {
        _isValid.value = (!name.value.isNullOrEmpty() && !email.value.isNullOrEmpty() && _isSamePassword.value!!)
    }
}