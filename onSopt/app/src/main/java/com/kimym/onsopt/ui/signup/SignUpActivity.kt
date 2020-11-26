package com.kimym.onsopt.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.data.api.RetrofitBuilder
import com.kimym.onsopt.data.api.user.UserRepository
import com.kimym.onsopt.databinding.ActivitySignUpBinding
import com.kimym.onsopt.ui.signin.SignInActivity
import com.kimym.onsopt.util.showToast
import com.kimym.onsopt.util.textChangedListener

class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel : SignUpViewModel by viewModels()
    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this

        val userRepository =
            UserRepository(RetrofitBuilder.userService)
        signUpViewModel.init(userRepository)
    }

    override fun onResume() {
        super.onResume()

        binding.etPw.textChangedListener { signUpViewModel.checkPassword() }

        binding.etPwCheck.textChangedListener { signUpViewModel.checkPassword() }

        signUpViewModel.isValid.observe(this, Observer { isValid->
            isValid?.let { if(isValid) signUpViewModel.signUp() else showToast("Invalid information") }
        })

        signUpViewModel.isSignUp.observe(this, Observer { isSignUp->
            isSignUp?.let { if(isSignUp) {
                setResult(1, Intent(this , SignInActivity::class.java))
                finish()
            } else showToast("Check information") }
        })
    }
}