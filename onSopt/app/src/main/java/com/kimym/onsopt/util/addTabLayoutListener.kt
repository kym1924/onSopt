package com.kimym.onsopt.util

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

fun TabLayout.addTabLayoutListener(viewPager : ViewPager){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabSelected(tab: TabLayout.Tab?) { viewPager.currentItem = tab!!.position }
    })
}