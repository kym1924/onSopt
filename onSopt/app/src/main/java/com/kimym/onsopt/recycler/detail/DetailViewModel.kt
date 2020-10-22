package com.kimym.onsopt.recycler.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimym.onsopt.room.User

class DetailViewModel : ViewModel(){

    val detailId = MutableLiveData<String>("")
    val detailName = MutableLiveData<String>("")
    val detailPart = MutableLiveData<String>("")
    val detailDate = MutableLiveData<String>("")

    fun init(user : User){
        detailId.value = "Id : " + user.id
        detailName.value = "Name : " + user.name
        detailPart.value = "Part : " + user.part
        detailDate.value = "SignUp DATE : " + user.date
    }
}