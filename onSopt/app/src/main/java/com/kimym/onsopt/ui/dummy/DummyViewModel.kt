package com.kimym.onsopt.ui.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimym.onsopt.R
import com.kimym.onsopt.data.model.DummyUserInfo

class DummyViewModel : ViewModel() {
    lateinit var allUsers : LiveData<List<DummyUserInfo>>

    private val _layoutItem = MutableLiveData<Int>(R.layout.item_recycler_linear)
    val layoutItem : LiveData<Int>
        get() = _layoutItem

    fun changeLayout(){
        if(_layoutItem.value==R.layout.item_recycler_linear) _layoutItem.value = R.layout.item_recycler_grid
        else _layoutItem.value = R.layout.item_recycler_linear
    }
}