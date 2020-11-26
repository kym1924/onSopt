package com.kimym.onsopt.ui.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.data.api.user.UserRepository
import com.kimym.onsopt.data.model.RequestSignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignInViewModel : ViewModel() {

    private lateinit var userRepository : UserRepository
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _isSignIn = MutableLiveData<Boolean>()
    val isSignIn : LiveData<Boolean>
        get() = _isSignIn

    private val _isValid = MutableLiveData<Boolean>()
    val isValid : LiveData<Boolean>
        get() = _isValid

    fun init(repository : UserRepository) {
        this.userRepository = repository
    }

    fun signIn() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val signIn= userRepository.requestSignIn(RequestSignIn(email = email.value!!, password = password.value!!))
            if(signIn.success) _isSignIn.postValue(true) }
        catch(e : HttpException) {
            Log.d("error", e.toString())
            _isSignIn.postValue(false)
        }
    }

    fun checkValidation() {
        _isValid.value = email.value.toString().isNotEmpty() && password.value.toString().isNotEmpty()
    }
}
