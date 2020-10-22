package com.kimym.onsopt.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDao

class RecyclerViewModel : ViewModel(){

    lateinit var userDao : UserDao
    lateinit var allUsers : LiveData<List<User>>

    fun init(dao : UserDao){
        userDao = dao
        allUsers = userDao.getAll()
    }
}