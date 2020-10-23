package com.kimym.onsopt.recycler.linear

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
import com.kimym.onsopt.util.recyclerItemTouchHelper
import com.kimym.onsopt.util.startActivityWithUser
import kotlinx.android.synthetic.main.activity_linear.*

class LinearActivity : AppCompatActivity() {

    private val recyclerViewModel : RecyclerViewModel by viewModels()
    private val linearAdapter : LinearAdapter = LinearAdapter(this)
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)

        linearAdapter.users = users
        rv_linear.adapter = linearAdapter
        recyclerItemTouchHelper(rv_linear, recyclerViewModel)

        val userDao = UserDatabase.getDatabase(this).userDao()
        recyclerViewModel.init(userDao)
    }

    override fun onStart(){
        super.onStart()

        recyclerViewModel.allUsers.observe(this, Observer { users ->
            users?.let { linearAdapter.setUsers(it) }
        })
    }

    override fun onResume(){
        super.onResume()

        linearAdapter.setItemClickListener(object :
            LinearAdapter.ItemClickListener {
            override fun onClick(view : View, user : User) {
                startActivityWithUser<DetailActivity>(user)
            }
        })
    }
}
