package com.kimym.onsopt.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentHomeBinding
import com.kimym.onsopt.room.UserDatabase

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by activityViewModels()
    lateinit var binding : FragmentHomeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val userDao = UserDatabase.getDatabase(context).userDao()

        homeViewModel.init(sharedPref, userDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getMyName()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.vpHome.adapter = HomePagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.vpHome)
        binding.tabLayout.apply{
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
    }
}