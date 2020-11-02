package com.kimym.onsopt.ui.signin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivitySignInBinding
import com.kimym.onsopt.ui.main.MainActivity
import com.kimym.onsopt.ui.signup.SignUpActivity
import com.kimym.onsopt.util.showToast
import com.kimym.onsopt.util.startActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val signInViewModel : SignInViewModel by viewModels()
    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.signInViewModel = signInViewModel

        sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        signInViewModel.getSharedPref(sharedPref)

        signInViewModel.setText()
        signInViewModel.autoLogin()

        if(signInViewModel.autoLogin.value!!){
            showToast("자동로그인")
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        btn_login.setOnClickListener {
            signInViewModel.validation()
            if (signInViewModel.isValid.value!!) {
                signInViewModel.putSharedPref()
                startActivity<MainActivity>()
                finish()
            }
        }
        
        tv_register.setOnClickListener { startActivity<SignUpActivity>() }
    }
}