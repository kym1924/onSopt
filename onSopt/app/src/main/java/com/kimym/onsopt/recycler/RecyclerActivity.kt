package com.kimym.onsopt.recycler

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        val userDao = UserDatabase.getDatabase(this).userDao()
        recyclerViewModel.init(userDao)
    }

    override fun onStart() {
        super.onStart()

        recyclerViewModel.allUsers.observe(this, Observer { users ->
            users?.let {
                adapter.setUsers(it)
                recyclerViewModel.resetList(userList, users)
            }
        })

        recyclerViewModel.layoutItem.observe(this, Observer { layoutItem ->
            layoutItem?.let {
                adapter.setLayout(it)
                recyclerViewModel.changeLayoutManager(rv_recycler)
                rv_recycler.adapter = adapter
            }
        })
    }

    override fun onResume(){
        super.onResume()

        rv_recycler.itemTouchHelper(recyclerViewModel, userList)
    }
}