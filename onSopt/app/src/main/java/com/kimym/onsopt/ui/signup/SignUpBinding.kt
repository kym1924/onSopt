package com.kimym.onsopt.ui.signup

import android.view.View
import androidx.databinding.BindingAdapter

object SignUpBinding {
    @BindingAdapter("passwordCheck")
    @JvmStatic
    fun passwordCheck(view: View, isSamePassword: Boolean) {
        view.visibility = if (isSamePassword) View.VISIBLE else View.INVISIBLE
    }
}