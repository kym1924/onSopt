package com.kimym.onsopt.ui.fragment

import android.content.Context
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
import com.kimym.onsopt.room.UserDatabase
import com.kimym.onsopt.ui.signin.SignInActivity
import com.kimym.onsopt.util.startActivity

class MyPageFragment : Fragment() {

    private val myPageViewModel : MyPageViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val userDao = UserDatabase.getDatabase(context).userDao()

        myPageViewModel.init(sharedPref, userDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myPageViewModel.getMy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMyPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        binding.myPageViewModel = myPageViewModel
        binding.user = myPageViewModel.my
        return binding.root
    }

    override fun onStart(){
        super.onStart()

        myPageViewModel.logout.observe(this, Observer{ logout ->
            logout.let { if(it) requireContext().startActivity<SignInActivity>() }
        })
    }
}