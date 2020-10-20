package com.kimym.onsopt.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.sopt27.room.UserDatabase
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivitySignUpBinding
import com.kimym.onsopt.signin.SignInActivity
import com.kimym.onsopt.util.showToast
import com.kimym.onsopt.util.textChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel : SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.signUpViewModel = signUpViewModel

        val userDao = UserDatabase.getDatabase(this).userDao()
        signUpViewModel.initDao(userDao)
    }

    override fun onResume(){
        super.onResume()

        btn_register.setOnClickListener {
            signUpViewModel.validation()
            if (signUpViewModel.isValid.value!!) {
                signUpViewModel.insert()
                showToast("회원가입완료")
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", et_id.text.toString())
                intent.putExtra("password", et_pw.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            } else showToast("입력하신정보를확인하세요")
        }

        et_pw.textChangedListener{
            signUpViewModel.checkPassword(et_pw.text.toString(), et_pw_check.text.toString())
        }

        et_pw_check.textChangedListener {
            signUpViewModel.checkPassword(et_pw.text.toString(), et_pw_check.text.toString())
        }

        signUpViewModel.isSamePassword.observe(this, Observer{ value->
            value.let {img_check_pw.visibility = if(value) View.VISIBLE else View.INVISIBLE }
        })
    }
}