package com.kimym.onsopt.recycler

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.ActivityDetailBinding
import com.kimym.onsopt.room.User

class DetailActivity : AppCompatActivity() {

    private val detailViewModel : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detailViewModel = detailViewModel

        val user = intent.getParcelableExtra<User>("user")
        detailViewModel.init(user!!)
    }
}