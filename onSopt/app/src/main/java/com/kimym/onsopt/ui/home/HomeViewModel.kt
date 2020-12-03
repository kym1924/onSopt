package com.kimym.onsopt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _tabItems = MutableLiveData<String>("INFO OTHER")
    val tabItems : LiveData<String>
        get() = _tabItems
}