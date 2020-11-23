package com.kimym.onsopt.util

import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kimym.onsopt.R

fun BottomNavigationView.setBottomNavigationListener(viewPager : ViewPager) {
    this.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nv_user -> viewPager.currentItem = 0
            R.id.nv_web -> viewPager.currentItem = 1
            R.id.nv_myPage -> viewPager.currentItem = 2
        }
        true
    }
}