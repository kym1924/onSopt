package com.kimym.onsopt.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimym.onsopt.data.api.user.UserRepository

class HomeViewModel @ViewModelInject constructor(
    private val userRepository : UserRepository
) : ViewModel() {

    private val _logout = MutableLiveData(false)
    val logout : LiveData<Boolean>
        get() = _logout

    fun logout() {
        userRepository.logout()
        _logout.value = true
    }
}
