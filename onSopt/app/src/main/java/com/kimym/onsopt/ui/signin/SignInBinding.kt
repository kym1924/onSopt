package com.kimym.onsopt.ui.signin

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.kimym.onsopt.room.User

object SignInBinding {
    @BindingAdapter("fromSignUpId")
    @JvmStatic
    fun fromSignUpId(editText : EditText, user : User?) {
        user?.let{editText.setText(user.id)}
    }

    @BindingAdapter("fromSignUpPassword")
    @JvmStatic
    fun fromSignUpPassword(editText : EditText, user : User?) {
        user?.let{editText.setText(user.password)}
    }
}