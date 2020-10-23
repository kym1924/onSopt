package com.kimym.onsopt.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerViewModel : ViewModel(){

    lateinit var userDao : UserDao
    lateinit var allUsers : LiveData<List<User>>

    fun init(dao : UserDao) {
        userDao = dao
        allUsers = userDao.getAll()
    }

    fun delete(position : Int) = viewModelScope.launch(Dispatchers.IO) {
        userDao.deleteUser(allUsers.value!![position])
    }

    fun changeIdx(from : Int, to : Int) = viewModelScope.launch(Dispatchers.IO){
        val max = userDao.maxIdx()
        userDao.changeIdx(allUsers.value!![from].idx, max+1)
        userDao.changeIdx(allUsers.value!![to].idx, allUsers.value!![from].idx)
        userDao.changeIdx(max+1, allUsers.value!![to].idx)
    }
}