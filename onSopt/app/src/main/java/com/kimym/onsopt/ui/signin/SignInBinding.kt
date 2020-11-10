package com.kimym.onsopt.ui.signin

import android.widget.EditText
import androidx.databinding.BindingAdapter

object SignInBinding {
    @BindingAdapter("fromSignUp")
    @JvmStatic
    fun fromSignUp(editText : EditText, content : String?) {
        content?.let{editText.setText(content)}
    }
}