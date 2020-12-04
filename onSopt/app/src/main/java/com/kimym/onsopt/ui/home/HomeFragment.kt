package com.kimym.onsopt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentHomeBinding
import com.kimym.onsopt.ui.signin.SignInActivity
import com.kimym.onsopt.util.startActivity

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this@HomeFragment

        setTabLayoutListener(binding)
        setHomeViewPager(binding)
        setLogout()

        return binding.root
    }

    private fun setHomeViewPager(binding : FragmentHomeBinding) {
        binding.vpHome.adapter = HomePagerAdapter(childFragmentManager)

        binding.vpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.tabLayout.getTabAt(position)!!.select()
            }
        })
    }

    private fun setTabLayoutListener(binding : FragmentHomeBinding) {

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("INFO"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("OTHER"))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) { binding.vpHome.currentItem = tab!!.position }
        })
    }

    private fun setLogout() {
        homeViewModel.logout.observe(viewLifecycleOwner, Observer{ logout ->
            logout?.let { if(logout) requireActivity().startActivity<SignInActivity>() }
        })
    }
}