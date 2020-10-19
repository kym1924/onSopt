package com.example.sopt27.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sopt27.R
import com.example.sopt27.databinding.ActivitySignInBinding
import com.example.sopt27.home.HomeActivity
import com.example.sopt27.signup.SignUpActivity
import com.example.sopt27.util.showToast
import com.example.sopt27.util.startActivity
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
            startActivity<HomeActivity>()
            finish()
        }
    }

    override fun onResume(){
        super.onResume()

        btn_login.setOnClickListener{
            signInViewModel.validation()
            if (signInViewModel.isValid.value!!) {
                signInViewModel.putSharedPref()
                startActivity<HomeActivity>()
                finish()
            }
        }

        tv_register.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            et_id.setText(data?.getStringExtra("id"))
            et_pw.setText(data?.getStringExtra("password"))
        }
    }
}