package com.kimym.onsopt.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomePagerAdapter (fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position:Int) : Fragment {
        return when(position){
            0-> InfoFragment()
            else-> OtherFragment()
        }
    }
    override fun getCount() = 2
}