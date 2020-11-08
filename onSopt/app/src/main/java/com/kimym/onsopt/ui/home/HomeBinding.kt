package com.kimym.onsopt.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter

object HomeBinding {
    @BindingAdapter("setMyName")
    @JvmStatic
    fun setMyName(tvName : TextView, myName : String?) {
        myName?.let{ tvName.text = myName }
    }
}