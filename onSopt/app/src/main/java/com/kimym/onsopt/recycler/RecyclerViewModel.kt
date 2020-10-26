package com.kimym.onsopt.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.R
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerViewModel : ViewModel(){

    lateinit var userDao : UserDao
    lateinit var allUsers : LiveData<List<User>>

    private val _layoutItem = MutableLiveData<Int>(R.layout.item_recycler_linear)
    val layoutItem : LiveData<Int>
        get() = _layoutItem

    fun init(dao : UserDao) {
        userDao = dao
        allUsers = userDao.getAll()
    }

    fun delete(position : Int) = viewModelScope.launch(Dispatchers.IO) {
        userDao.deleteUser(allUsers.value!![position])
    }

    fun update(users : List<User>) = viewModelScope.launch(Dispatchers.IO) {
        userDao.updateIdx(users)
    }

    fun resetList(userList : MutableList<User>, users : List<User>) {
        userList.clear()
        userList.addAll(users)
    }

    fun changeLayout(){
        if(_layoutItem.value==R.layout.item_recycler_linear) _layoutItem.value = R.layout.item_recycler_grid
        else _layoutItem.value = R.layout.item_recycler_linear
    }
}