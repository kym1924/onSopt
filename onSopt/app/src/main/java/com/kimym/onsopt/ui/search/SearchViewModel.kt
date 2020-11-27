package com.kimym.onsopt.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.data.api.search.SearchRepository
import com.kimym.onsopt.data.model.WebData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val keyword = MutableLiveData<String>("")
    private lateinit var repository : SearchRepository

    private val _allWeb = MutableLiveData<WebData>()
    val allWeb : LiveData<WebData>
        get() = _allWeb

    private val _page = MutableLiveData<Int>(1)
    val page : LiveData<Int>
        get() = _page

    fun init(repository : SearchRepository) {
        this.repository = repository
    }

    fun resetKeyword() {
        keyword.value=""
    }
    
    fun resetPage() {
        _page.value = 1
    }

    fun getWebSearch() = viewModelScope.launch(Dispatchers.IO){
        _allWeb.postValue(repository.getWebSearch(keyword.value!!, page.value!!))
        if(page.value!!<50) _page.postValue(_page.value!!+1)
    }
}