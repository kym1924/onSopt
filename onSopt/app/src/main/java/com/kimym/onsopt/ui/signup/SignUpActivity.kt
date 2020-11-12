package com.kimym.onsopt.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivitySignUpBinding
import com.kimym.onsopt.room.UserDatabase
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

        val userDao = UserDatabase.getDatabase(this).userDao()
        signUpViewModel.initDao(userDao)
    }

    override fun onResume() {
        super.onResume()

        binding.etPw.textChangedListener { signUpViewModel.checkPassword() }

        binding.etPwCheck.textChangedListener { signUpViewModel.checkPassword() }

        signUpViewModel.isValid.observe(this, Observer { isValid ->
            isValid.let { if(isValid) signUpViewModel.insert() else showToast("Invalid Information")}
        })

        signUpViewModel.isSuccess.observe(this, Observer { isSuccess ->
            isSuccess.let {
                if(isSuccess) {
                    showToast("Success")
                    val intent = Intent(this, SignInActivity::class.java)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        })
    }
}