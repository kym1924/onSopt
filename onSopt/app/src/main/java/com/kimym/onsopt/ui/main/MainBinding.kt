package com.kimym.onsopt.ui.main

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kimym.onsopt.util.addViewPagerListener
import com.kimym.onsopt.util.setBottomNavigationListener

object MainBinding {
    @BindingAdapter("mainAdapter")
    @JvmStatic
    fun mainAdapter(viewPager : ViewPager, fragmentManager : FragmentManager) {
        viewPager.adapter = MainPagerAdapter(fragmentManager)
    }

    @BindingAdapter("bottomListener")
    @JvmStatic
    fun bottomListener(bottomNavigationView : BottomNavigationView, viewPager : ViewPager) {
        bottomNavigationView.setBottomNavigationListener(viewPager)
    }

    @BindingAdapter("viewPagerListener")
    @JvmStatic
    fun viewPagerListener(viewPager : ViewPager, bottomNavigationView : BottomNavigationView) {
        viewPager.addViewPagerListener(bottomNavigationView)
    }
}