package com.kimym.onsopt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kimym.onsopt.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart(){
        super.onStart()

        vp_home.adapter = HomePagerAdapter(requireActivity().supportFragmentManager)
        tab_layout.setupWithViewPager(vp_home)
        tab_layout.apply{
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
    }
}