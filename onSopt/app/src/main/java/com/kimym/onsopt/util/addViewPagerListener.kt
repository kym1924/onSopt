package com.kimym.onsopt.util

import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

fun ViewPager.addViewPagerListener(bottomNavigationView : BottomNavigationView){
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) { bottomNavigationView.menu.getItem(position).isChecked = true }
    })
}