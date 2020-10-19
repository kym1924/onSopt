package com.example.sopt27.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sopt27.R
import com.example.sopt27.databinding.ActivitySignUpBinding
import com.example.sopt27.signin.SignInActivity
import com.example.sopt27.util.showToast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel : SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.signUpViewModel = signUpViewModel
    }

    override fun onResume(){
        super.onResume()

        btn_register.setOnClickListener {
            signUpViewModel.validation()
            if (signUpViewModel.isValid.value!!) {
                showToast("회원가입완료")
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", et_id.text.toString())
                intent.putExtra("password", et_pw.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
            else showToast("입력하신정보를확인하세요")
        }
    }
}