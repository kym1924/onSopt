package com.kimym.onsopt.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.data.api.kakao.KakaoRepository
import com.kimym.onsopt.data.model.KakaoWebData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val keyword = MutableLiveData<String>("")
    private lateinit var repository : KakaoRepository

    private val _allWeb = MutableLiveData<KakaoWebData>()
    val allWeb : LiveData<KakaoWebData>
        get() = _allWeb

    private val _page = MutableLiveData<Int>(1)
    val page : LiveData<Int>
        get() = _page

    fun init(repository : KakaoRepository) {
        this.repository = repository
    }

    fun resetKeyword() {
        keyword.value=""
    }

    fun getKakaoWebSearch() = viewModelScope.launch(Dispatchers.IO){
        _allWeb.postValue(repository.getKakaoWebSearch(keyword.value!!, page.value!!))
        if(page.value!!<50) _page.postValue(_page.value!!+1)
    }
}