package com.kimym.onsopt.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setMainViewPager(binding)
        setBottomNavigationListener(binding)
    }

    private fun setMainViewPager(binding : ActivityMainBinding) {
        binding.vpMain.adapter = MainPagerAdapter(supportFragmentManager)

        binding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) { binding.bottomNavigation.menu.getItem(position).isChecked = true }
        })
    }

    private fun setBottomNavigationListener(binding : ActivityMainBinding) {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nv_home -> binding.vpMain.currentItem = 0
                R.id.nv_user -> binding.vpMain.currentItem = 1
                R.id.nv_search -> binding.vpMain.currentItem = 2
            }
            true
        }
    }
}