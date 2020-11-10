package com.kimym.onsopt.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

object HomeBinding {
    @BindingAdapter("setMyName")
    @JvmStatic
    fun setMyName(tvName : TextView, myName : String?) {
        myName?.let{ tvName.text = myName }
    }

    @BindingAdapter("setUpViewPager")
    @JvmStatic
    fun setUpViewPager(tabLayout : TabLayout, viewPager : ViewPager) {
        tabLayout.setupWithViewPager(viewPager)
    }

    @BindingAdapter("setTabItems")
    @JvmStatic
    fun setTabItems(tabLayout : TabLayout, tabItems : String) {
        tabLayout.apply{
            getTabAt(0)?.text = tabItems.split(" ")[0]
            getTabAt(1)?.text = tabItems.split(" ")[1]
        }
    }
}