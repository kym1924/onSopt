package com.kimym.onsopt.ui.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kimym.onsopt.data.api.search.SearchRepository
import com.kimym.onsopt.data.model.WebData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val repository : SearchRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
): ViewModel() {

    val keyword = MutableLiveData<String>("")

    private val _allWeb = MutableLiveData<WebData>()
    val allWeb : LiveData<WebData>
        get() = _allWeb

    private val _page = MutableLiveData(1)
    val page : LiveData<Int>
        get() = _page

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