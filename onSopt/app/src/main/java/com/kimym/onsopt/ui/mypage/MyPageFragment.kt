package com.kimym.onsopt.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentMyPageBinding
import com.kimym.onsopt.ui.signin.SignInActivity
import com.kimym.onsopt.util.startActivity

class MyPageFragment : Fragment() {

    private val myPageViewModel : MyPageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMyPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        binding.myPageViewModel = myPageViewModel
        return binding.root
    }

    override fun onStart(){
        super.onStart()

        myPageViewModel.logout.observe(this, Observer{ logout ->
            logout.let { if(it) requireActivity().startActivity<SignInActivity>() }
        })
    }
}