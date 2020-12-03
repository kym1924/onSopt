package com.kimym.onsopt.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.baoyz.widget.PullRefreshLayout
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.RetrofitBuilder
import com.kimym.onsopt.data.api.search.SearchRepository
import com.kimym.onsopt.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.searchViewModel = searchViewModel
        binding.lifecycleOwner = this

        val searchRepository = SearchRepository(RetrofitBuilder.searchService)
        searchViewModel.init(searchRepository)

        setAdapter(binding)
        setSearchListener(binding)
        setSwipeListener(binding)

        return binding.root
    }

    private fun setSearchListener(binding : FragmentSearchBinding) {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(binding.etSearch.text.toString().isNotEmpty()) {
                    searchViewModel.getWebSearch()
                    searchViewModel.resetPage()
                    binding.etSearch.clearFocus()
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setSwipeListener(binding : FragmentSearchBinding) {
        binding.layoutSearchSwipe.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL)
        binding.layoutSearchSwipe.setOnRefreshListener {
            searchViewModel.getWebSearch()
            binding.layoutSearchSwipe.setRefreshing(false)
        }
    }

    private fun setAdapter(binding : FragmentSearchBinding) {
        val searchAdapter = SearchAdapter()
        binding.rvSearchResult.adapter = searchAdapter
    }
}