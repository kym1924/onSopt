package com.kimym.onsopt.ui.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentDummyBinding

class DummyFragment : Fragment() {
    private val dummyViewModel : DummyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentDummyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dummy, container, false)
        binding.dummyViewModel = dummyViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}