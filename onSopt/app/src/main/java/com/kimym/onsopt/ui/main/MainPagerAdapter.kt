package com.kimym.onsopt.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kimym.onsopt.ui.dummy.DummyFragment
import com.kimym.onsopt.ui.home.HomeFragment
import com.kimym.onsopt.ui.search.SearchFragment

class MainPagerAdapter (fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position:Int) : Fragment {
        return when(position){
            0-> HomeFragment()
            1-> DummyFragment()
            else-> SearchFragment()
        }
    }
    override fun getCount() = 3
}