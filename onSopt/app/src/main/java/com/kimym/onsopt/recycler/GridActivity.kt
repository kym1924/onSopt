package com.kimym.onsopt.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt27.room.User
import com.kimym.onsopt.R
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {

    lateinit var gridAdapter : GridAdapter
    val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        gridAdapter = GridAdapter(this)
        gridAdapter.users = users
        rv_grid.adapter = gridAdapter
    }

    override fun onResume(){
        super.onResume()
        gridAdapter.notifyDataSetChanged()
    }
}