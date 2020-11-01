package com.kimym.onsopt.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimym.onsopt.R
import com.kimym.onsopt.util.addViewPagerListener
import com.kimym.onsopt.util.setBottomNavigationListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp_main.adapter = MainPagerAdapter(supportFragmentManager)
    }

    override fun onStart(){
        super.onStart()

        vp_main.addViewPagerListener(bottom_navigation)
        bottom_navigation.setBottomNavigationListener(vp_main)
    }
}