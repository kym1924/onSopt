package com.kimym.onsopt.ui.signin

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kimym.onsopt.data.api.user.UserRepository
import com.kimym.onsopt.data.model.RequestSignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignInViewModel @ViewModelInject constructor(
    private val userRepository : UserRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
): ViewModel() {

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _isSignIn = MutableLiveData<Boolean>()
    val isSignIn : LiveData<Boolean>
        get() = _isSignIn

    private val _isValid = MutableLiveData<Boolean>()
    val isValid : LiveData<Boolean>
        get() = _isValid

    fun autoLogin() {
        email.value = userRepository.getEmail()
        password.value = userRepository.getPassword()
        if(email.value == "" || password.value == "") _isSignIn.value = null
        else _isSignIn.value = true
    }

    fun signIn() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val signIn= userRepository.requestSignIn(RequestSignIn(email = email.value!!, password = password.value!!))
            if(signIn.success) {
                userRepository.putEmail(email.value!!)
                userRepository.putPassword(password.value!!)
                _isSignIn.postValue(true)
            } }
        catch(e : HttpException) {
            Log.d("error", e.toString())
            _isSignIn.postValue(false)
        }
    }

    fun checkValidation() {
        _isValid.value = email.value.toString().isNotEmpty() && password.value.toString().isNotEmpty()
    }
}
