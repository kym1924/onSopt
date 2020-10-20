package com.kimym.onsopt.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData<String>("")
    val id = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val passwordCheck = MutableLiveData<String>("")

    private val _isSamePassword = MutableLiveData<Boolean>(false)
    val isSamePassword : LiveData<Boolean>
        get() = _isSamePassword

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid : LiveData<Boolean>
        get() = _isValid

    fun checkPassword(password : String, passwordCheck : String){
        _isSamePassword.value = password == passwordCheck
    }

    fun validation() {
        _isValid.value = false
        if(!name.value.isNullOrEmpty() && !id.value.isNullOrEmpty() && _isSamePassword.value!!) _isValid.value = true
    }
}