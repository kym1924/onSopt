package com.kimym.onsopt.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.RetrofitBuilder
import com.kimym.onsopt.data.api.user.UserRepository
import com.kimym.onsopt.databinding.ActivitySignInBinding
import com.kimym.onsopt.ui.main.MainActivity
import com.kimym.onsopt.ui.signup.SignUpActivity
import com.kimym.onsopt.util.showToast
import com.kimym.onsopt.util.startActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private val signInViewModel : SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.signInViewModel = signInViewModel
        binding.lifecycleOwner = this

        val userRepository =
            UserRepository(RetrofitBuilder.userService)
        signInViewModel.init(userRepository)
    }

    override fun onStart() {
        super.onStart()

        signInViewModel.isValid.observe(this, Observer { isValid->
            isValid?.let { if(isValid) signInViewModel.signIn() else showToast("Invalid information") }
        })

        signInViewModel.isSignIn.observe(this, Observer { isSignIn->
            isSignIn?.let { if(isSignIn) { startActivity<MainActivity>() } else showToast("fail") }
        })
    }

    override fun onResume() {
        super.onResume()

        binding.tvRegister.setOnClickListener{ startActivityForResult(Intent(this, SignUpActivity::class.java),1) }
    }
}