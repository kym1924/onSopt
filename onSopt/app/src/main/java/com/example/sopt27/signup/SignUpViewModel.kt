package com.example.sopt27.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isValid = MutableLiveData<Boolean>()

    init{
        name.value = ""
        id.value = ""
        password.value = ""
        isValid.value = false
    }

    fun validation() {
        isValid.value = false
        if(!name.value.isNullOrEmpty() && !id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) isValid.value = true
    }
}