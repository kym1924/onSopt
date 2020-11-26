package com.kimym.onsopt.ui.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.DummyRepository
import com.kimym.onsopt.data.model.DummyUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DummyViewModel : ViewModel() {
    private lateinit var dummyRepository : DummyRepository

    private val _layoutItem = MutableLiveData<Int>(R.layout.item_recycler_linear)
    val layoutItem : LiveData<Int>
        get() = _layoutItem

    private val _allUsers = MutableLiveData<List<DummyUserInfo>>()
    val allUsers : LiveData<List<DummyUserInfo>>
        get() = _allUsers

    fun init(repository : DummyRepository) {
        this.dummyRepository = repository
    }

    fun changeLayout(){
        if(_layoutItem.value==R.layout.item_recycler_linear) _layoutItem.value = R.layout.item_recycler_grid
        else _layoutItem.value = R.layout.item_recycler_linear
    }

    fun getDummyUsers() = viewModelScope.launch(Dispatchers.IO) {
        _allUsers.postValue(dummyRepository.getDummyUsers().data)
    }
}