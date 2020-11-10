package com.kimym.onsopt.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivityMainBinding
import com.kimym.onsopt.util.addViewPagerListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply{
            fragmentManager = supportFragmentManager
            viewPager = binding.vpMain
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onStart(){
        super.onStart()

        vp_main.addViewPagerListener(bottom_navigation)
    }
}