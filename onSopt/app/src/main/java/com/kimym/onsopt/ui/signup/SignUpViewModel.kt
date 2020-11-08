package com.kimym.onsopt.ui.signup

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

    private lateinit var userDao : UserDao

    fun initDao(dao : UserDao) {
        userDao = dao
    }

    fun checkPassword() {
        _isSamePassword.value = (password.value == passwordCheck.value && !password.value.isNullOrEmpty())
    }

    fun validation() {
        _isValid.value = (!name.value.isNullOrEmpty() && !id.value.isNullOrEmpty() && _isSamePassword.value!!)
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        val user = User(idx = 0, name = name.value!!, id = id.value!!, password = password.value!!)
        userDao.insertUser(user)
        _isSuccess.postValue(true)
    }
}