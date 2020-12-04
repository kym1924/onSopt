package com.kimym.onsopt.ui.dummy

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.dummy.DummyRepository
import com.kimym.onsopt.data.model.DummyUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DummyViewModel @ViewModelInject constructor(
    private val dummyRepository : DummyRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
): ViewModel() {

    private val _layoutItem = MutableLiveData<Int>(R.layout.item_dummy_linear)
    val layoutItem : LiveData<Int>
        get() = _layoutItem

    private val _allUsers = MutableLiveData<List<DummyUserInfo>>()
    val allUsers : LiveData<List<DummyUserInfo>>
        get() = _allUsers

    private val _page = MutableLiveData(1)
    val page : LiveData<Int>
        get() = _page

    fun changeLayout(){
        if(_layoutItem.value==R.layout.item_dummy_linear) _layoutItem.value = R.layout.item_dummy_grid
        else _layoutItem.value = R.layout.item_dummy_linear
    }

    fun getDummyUsers() = viewModelScope.launch(Dispatchers.IO) {
        if(_page.value==1) _page.postValue(2) else _page.postValue(1)
        _allUsers.postValue(dummyRepository.getDummyUsers(page.value!!).data)
    }
}