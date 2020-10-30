package com.kimym.onsopt.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimym.onsopt.R
import com.kimym.onsopt.recycler.RecyclerActivity
import com.kimym.onsopt.signin.SignInActivity
import com.kimym.onsopt.util.startActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit = sharedPref.edit()

        btn_logout.setOnClickListener{
            sharedEdit.clear()
            sharedEdit.apply()
            startActivity<SignInActivity>()
            finish()
        }

        btn_recycler.setOnClickListener{
            startActivity<RecyclerActivity>()
        }
    }
}