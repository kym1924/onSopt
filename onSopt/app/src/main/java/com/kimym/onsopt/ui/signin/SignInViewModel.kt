package com.kimym.onsopt.ui.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.data.api.UserRepository
import com.kimym.onsopt.data.model.RequestSignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignInViewModel : ViewModel() {

    private lateinit var userRepository : UserRepository
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin : LiveData<Boolean>
        get() = _isLogin

    private val _isValid = MutableLiveData<Boolean>()
    val isValid : LiveData<Boolean>
        get() = _isValid

    fun init(repository : UserRepository) {
        this.userRepository = repository
    }

    fun login() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val login= userRepository.requestSignIn(RequestSignIn(email = email.value!!, password = password.value!!))
            if(login.success) _isLogin.postValue(true) }
        catch(e : HttpException) {
            Log.d("error", e.toString())
            _isLogin.postValue(false)
        }
    }

    fun checkValidation() {
        _isValid.value = email.value.toString().isNotEmpty() && password.value.toString().isNotEmpty()
    }
}
