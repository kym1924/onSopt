package com.kimym.onsopt.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

object HomeBinding {
    @BindingAdapter("setMyName")
    @JvmStatic
    fun setMyName(tvName : TextView, myName : String?) {
        myName?.let{ tvName.text = myName }
    }

    @BindingAdapter("setTabItems")
    @JvmStatic
    fun setTabItems(tabLayout : TabLayout, tabItems : String) {
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[0])) ;
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[1])) ;
    }
}