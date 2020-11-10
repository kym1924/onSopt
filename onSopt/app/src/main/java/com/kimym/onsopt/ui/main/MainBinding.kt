package com.kimym.onsopt.ui.main

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

object MainBinding {
    @BindingAdapter("mainAdapter")
    @JvmStatic
    fun mainAdapter(viewPager : ViewPager, fragmentManager : FragmentManager) {
        viewPager.adapter = MainPagerAdapter(fragmentManager)
    }
}