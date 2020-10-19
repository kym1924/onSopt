package com.example.sopt27.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()

    private val _isSamePassword = MutableLiveData<Boolean>(false)
    val isSamePassword : LiveData<Boolean>
        get() = _isSamePassword

    val isValid = MutableLiveData<Boolean>()

    init{
        name.value = ""
        id.value = ""
        password.value = ""
        passwordCheck.value = ""
        isValid.value = false
    }

    fun checkPassword(password : String, passwordCheck : String){
        _isSamePassword.value = password == passwordCheck
    }

    fun validation() {
        isValid.value = false
        if(!name.value.isNullOrEmpty() && !id.value.isNullOrEmpty() && isSamePassword.value!!) isValid.value = true
    }
}