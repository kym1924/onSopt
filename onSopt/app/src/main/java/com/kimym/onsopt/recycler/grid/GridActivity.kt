package com.kimym.onsopt.recycler.grid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.recycler.RecyclerViewModel
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDatabase
import com.kimym.onsopt.util.recyclerItemTouchHelper
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {

    private val recyclerViewModel : RecyclerViewModel by viewModels()
    private val gridAdapter : GridAdapter = GridAdapter(this)
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        rv_grid.adapter = gridAdapter
        recyclerItemTouchHelper(rv_grid, recyclerViewModel, userList)

        val userDao = UserDatabase.getDatabase(this).userDao()
        recyclerViewModel.init(userDao)
    }

    override fun onStart(){
        super.onStart()

        recyclerViewModel.allUsers.observe(this, Observer { users ->
            users?.let {
                recyclerViewModel.resetList(userList, users)
                gridAdapter.setUsers(it) }
        })
    }
}