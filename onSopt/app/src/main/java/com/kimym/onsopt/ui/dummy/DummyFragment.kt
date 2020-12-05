package com.kimym.onsopt.ui.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.baoyz.widget.PullRefreshLayout
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentDummyBinding
import com.kimym.onsopt.databinding.ItemDummyLinearBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DummyFragment : Fragment() {
    private val dummyViewModel : DummyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentDummyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dummy, container, false)
        binding.dummyViewModel = dummyViewModel
        binding.lifecycleOwner = this

        setDummyList()
        setDummyAdapter(binding)
        setSwipeListener(binding)

        return binding.root
    }

    private fun setDummyList() {
        dummyViewModel.getDummyUsers()
    }

    private fun setDummyAdapter(binding : FragmentDummyBinding) {
        val adapter = DummyAdapter<ItemDummyLinearBinding>()
        dummyViewModel.layoutItem.observe(viewLifecycleOwner, Observer { layoutItem ->
            layoutItem?.let {
                adapter.setLayout(layoutItem)
                binding.rvDummy.adapter = adapter
            }
        })
    }

    private fun setSwipeListener(binding : FragmentDummyBinding) {
        binding.layoutDummySwipe.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL)
        binding.layoutDummySwipe.setOnRefreshListener {
            dummyViewModel.getDummyUsers()
            binding.layoutDummySwipe.setRefreshing(false)
        }
    }
}