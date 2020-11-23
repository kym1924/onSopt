package com.kimym.onsopt.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kimym.onsopt.ui.dummy.DummyFragment
import com.kimym.onsopt.ui.mypage.MyPageFragment
import com.kimym.onsopt.ui.search.SearchFragment

class MainPagerAdapter (fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position:Int) : Fragment {
        return when(position){
            0-> DummyFragment()
            1-> SearchFragment()
            else-> MyPageFragment()
        }
    }
    override fun getCount() = 3
}