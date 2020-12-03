package com.kimym.onsopt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by activityViewModels()
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeViewModel = homeViewModel
        binding.fragmentManager = childFragmentManager
        binding.viewPager = binding.vpHome
        binding.bindingTabLayout = binding.tabLayout
        binding.lifecycleOwner = this@HomeFragment
        return binding.root
    }
}