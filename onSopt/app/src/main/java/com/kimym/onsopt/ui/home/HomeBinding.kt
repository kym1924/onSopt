package com.kimym.onsopt.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kimym.onsopt.util.addTabLayoutListener

object HomeBinding {
    @BindingAdapter("setMyName")
    @JvmStatic
    fun setMyName(tvName : TextView, myName : String?) {
        myName?.let{ tvName.text = myName }
    }

    @BindingAdapter("homeAdapter")
    @JvmStatic
    fun homeAdapter(viewPager : ViewPager, fragmentManager : FragmentManager) {
        viewPager.adapter = HomePagerAdapter(fragmentManager)
    }

    @BindingAdapter("setTabListener")
    @JvmStatic
    fun setTabListener(tabLayout : TabLayout, viewPager : ViewPager) {
        tabLayout.addTabLayoutListener(viewPager)
    }

    @BindingAdapter("setTabItems")
    @JvmStatic
    fun setTabItems(tabLayout : TabLayout, tabItems : String) {
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[0])) ;
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[1])) ;
    }
}