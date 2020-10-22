package com.kimym.onsopt.recycler.grid

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.recycler.RecyclerViewModel
import com.kimym.onsopt.recycler.detail.DetailActivity
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDatabase
import com.kimym.onsopt.util.startActivityWithUser
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {

    private val recyclerViewModel : RecyclerViewModel by viewModels()
    private val gridAdapter : GridAdapter = GridAdapter(this)
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        gridAdapter.users = users
        rv_grid.adapter = gridAdapter

        val userDao = UserDatabase.getDatabase(this).userDao()
        recyclerViewModel.init(userDao)
    }

    override fun onStart(){
        super.onStart()

        recyclerViewModel.allUsers.observe(this, Observer { users ->
            users?.let { gridAdapter.setUsers(it) }
        })
    }

    override fun onResume(){
        super.onResume()

        gridAdapter.setItemClickListener(object :
            GridAdapter.ItemClickListener {
            override fun onClick(view : View, user : User) {
                startActivityWithUser<DetailActivity>(user)
            }
        })
    }
}