package com.kimym.onsopt.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt27.room.User
import com.kimym.onsopt.R
import kotlinx.android.synthetic.main.activity_linear.*

class LinearActivity : AppCompatActivity() {

    lateinit var linearAdapter : LinearAdapter
    val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)

        linearAdapter = LinearAdapter(this)
        linearAdapter.users = users
        rv_linear.adapter = linearAdapter
    }

    override fun onResume(){
        super.onResume()
        linearAdapter.notifyDataSetChanged()
    }
}
