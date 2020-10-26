package com.kimym.onsopt.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivityRecyclerBinding
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDatabase
import com.kimym.onsopt.util.itemTouchHelper
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    private val recyclerViewModel : RecyclerViewModel by viewModels()
    private val adapter : RecyclerAdapter = RecyclerAdapter(this)
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityRecyclerBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler)
        binding.recyclerViewModel = recyclerViewModel

        rv_recycler.adapter = adapter
        rv_recycler.itemTouchHelper(recyclerViewModel, userList)

        val userDao = UserDatabase.getDatabase(this).userDao()
        recyclerViewModel.init(userDao)
    }

    override fun onStart(){
        super.onStart()

        recyclerViewModel.allUsers.observe(this, Observer { users ->
            users?.let {
                recyclerViewModel.resetList(userList, users)
                adapter.setUsers(it) }
        })

        recyclerViewModel.layoutItem.observe(this, Observer {layoutItem ->
            layoutItem?.let {
                adapter.setLayout(layoutItem)
                rv_recycler.adapter = adapter
            }
        })
    }
}