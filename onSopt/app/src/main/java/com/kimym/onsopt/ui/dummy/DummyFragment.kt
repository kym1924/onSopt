package com.kimym.onsopt.ui.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.RetrofitBuilder
import com.kimym.onsopt.data.api.dummy.DummyRepository
import com.kimym.onsopt.databinding.FragmentDummyBinding
import com.kimym.onsopt.databinding.ItemRecyclerLinearBinding

class DummyFragment : Fragment() {
    private val dummyViewModel : DummyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentDummyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dummy, container, false)
        binding.dummyViewModel = dummyViewModel
        binding.lifecycleOwner = this

        val dummyRepository =
            DummyRepository(RetrofitBuilder.dummyService)
        dummyViewModel.init(dummyRepository)

        val adapter = DummyAdapter<ItemRecyclerLinearBinding>()

        setDummyAdapter(binding, adapter)
        setSwipeListener(binding)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dummyViewModel.getDummyUsers(dummyViewModel.page.value!!)
    }

    private fun setDummyAdapter(binding : FragmentDummyBinding, adapter : DummyAdapter<ItemRecyclerLinearBinding>){
        dummyViewModel.layoutItem.observe(viewLifecycleOwner, Observer { layoutItem ->
            layoutItem?.let {
                adapter.setLayout(layoutItem)
                binding.rvDummy.adapter = adapter
            }
        })
    }
    private fun setSwipeListener(binding : FragmentDummyBinding){
        binding.swipeLayout.setOnRefreshListener {
            dummyViewModel.getDummyUsers(dummyViewModel.page.value!!)
            binding.swipeLayout.isRefreshing = false
        }
    }
}