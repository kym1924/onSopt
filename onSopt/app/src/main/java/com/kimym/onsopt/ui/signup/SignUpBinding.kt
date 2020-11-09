package com.kimym.onsopt.ui.signup

import android.view.View
import androidx.databinding.BindingAdapter
import com.kimym.onsopt.R

object SignUpBinding {
    @BindingAdapter("passwordCheck")
    @JvmStatic
    fun passwordCheck(view: View, isSamePassword: Boolean) {
        view.visibility = if (isSamePassword) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("ifActive")
    @JvmStatic
    fun ifActive(view : View, content : String) {
        when (content) {
            "" -> view.setBackgroundResource(R.drawable.background_edit_text)
            else -> view.setBackgroundResource(R.drawable.background_edit_text_active)
        }
    }
}