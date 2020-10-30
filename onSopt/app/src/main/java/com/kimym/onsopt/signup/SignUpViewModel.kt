package com.kimym.onsopt.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData<String>("")
    val id = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _isSamePassword = MutableLiveData<Boolean>(false)
    val isSamePassword : LiveData<Boolean>
        get() = _isSamePassword

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid : LiveData<Boolean>
        get() = _isValid

    private lateinit var userDao : UserDao

    fun initDao(dao : UserDao){
        userDao = dao
    }

    fun checkPassword(password : String, passwordCheck : String){
        _isSamePassword.value = password == passwordCheck
    }

    fun validation() {
        _isValid.value = false
        if(!name.value.isNullOrEmpty() && !id.value.isNullOrEmpty() && _isSamePassword.value!!) _isValid.value = true
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        if (isValid.value!!) {
            val user = User(idx = 0, name = name.value!!, id = id.value!!, password = password.value!!)
            userDao.insertUser(user)
        }
    }
}