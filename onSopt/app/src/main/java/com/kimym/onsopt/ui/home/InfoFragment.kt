package com.kimym.onsopt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kimym.onsopt.R
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onResume() {
        super.onResume()

        tv_info.scaleX = 0f
        tv_info.scaleY = 0f
        tv_info.animate().apply {
            duration=2000
            scaleX(1f)
            scaleY(1f)
        }.start()

    }
}