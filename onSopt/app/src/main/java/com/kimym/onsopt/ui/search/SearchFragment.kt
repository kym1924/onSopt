package com.kimym.onsopt.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.RetrofitBuilder
import com.kimym.onsopt.data.api.kakao.KakaoRepository
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

        val kakaoRepository = KakaoRepository(RetrofitBuilder.kakaoService)
        searchViewModel.init(kakaoRepository)

        val adapter = SearchAdapter()
        binding.rvSearchResult.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        searchViewModel.getKakaoWebSearch()
    }
}