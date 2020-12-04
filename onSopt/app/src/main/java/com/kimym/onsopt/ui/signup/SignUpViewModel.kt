package com.kimym.onsopt.ui.signup

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kimym.onsopt.data.api.user.UserRepository
import com.kimym.onsopt.data.model.RequestSignUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpViewModel @ViewModelInject constructor(
    private val userRepository : UserRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
): ViewModel() {

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

    private val _isSignUp = MutableLiveData<Boolean>()
    val isSignUp : LiveData<Boolean>
        get() = _isSignUp

    fun checkPassword() {
        _isSamePassword.value = (password.value == passwordCheck.value && !password.value.isNullOrEmpty())
    }

    fun signUp() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val signUp = userRepository.requestSignUp(RequestSignUp(email = email.value!!, password = password.value!!, userName = name.value!!))
            if(signUp.success) _isSignUp.postValue(true) }
        catch(e : HttpException) {
            Log.d("error", e.toString())
            _isSignUp.postValue(false)
        }
    }

    fun checkValidation() {
        _isValid.value = (!name.value.isNullOrEmpty() && !email.value.isNullOrEmpty() && _isSamePassword.value!!)
    }
}