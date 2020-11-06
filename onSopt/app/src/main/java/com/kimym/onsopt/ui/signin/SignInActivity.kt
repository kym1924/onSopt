package com.kimym.onsopt.ui.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivitySignInBinding
import com.kimym.onsopt.room.UserDatabase
import com.kimym.onsopt.ui.main.MainActivity
import com.kimym.onsopt.ui.signup.SignUpActivity
import com.kimym.onsopt.util.showToast
import com.kimym.onsopt.util.startActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val signInViewModel : SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.signInViewModel = signInViewModel

        val sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val userDao = UserDatabase.getDatabase(this).userDao()
        signInViewModel.init(sharedPref, userDao)

        signInViewModel.autoLogin.observe(this, Observer{ it ->
            it.let { if(it) {
                showToast("자동로그인")
                startActivity<MainActivity>()
            }}
        })
    }

    override fun onResume(){
        super.onResume()

        signInViewModel.isValid.observe(this, Observer{ it ->
            it.let { if(it) {
                signInViewModel.putSharedPref()
                startActivity<MainActivity>()
            }}
        })

        tv_register.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
            signInViewModel.fromSignUp()
    }

    override fun onRestart(){
        super.onRestart()

        signInViewModel.fromSignUp.observe(this, Observer{ user ->
            user?.let{
                et_id.setText(user.id)
                et_pw.setText(user.password)
            }
        })
    }
}
