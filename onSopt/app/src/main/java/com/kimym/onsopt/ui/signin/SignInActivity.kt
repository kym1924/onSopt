package com.kimym.onsopt.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivitySignInBinding
import com.kimym.onsopt.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private val signInViewModel : SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.signInViewModel = signInViewModel
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()

        binding.tvRegister.setOnClickListener{ startActivityForResult(Intent(this, SignUpActivity::class.java),1) }
    }
}