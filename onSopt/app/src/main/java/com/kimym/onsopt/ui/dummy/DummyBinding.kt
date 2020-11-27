package com.kimym.onsopt.ui.dummy

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimym.onsopt.R
import com.kimym.onsopt.data.model.DummyUserInfo

object DummyBinding {
    @BindingAdapter("setImage")
    @JvmStatic
    fun setImage(imageView : ImageView, url : String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_loading_glide)
            .error(R.drawable.ic_error_glide)
            .into(imageView)
    }

    @BindingAdapter("setLayout")
    @JvmStatic
    fun setLayout(recyclerView : RecyclerView, layoutItem : Int) {
        if(layoutItem == R.layout.item_dummy_linear) recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        else recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
    }

    @BindingAdapter("setUsers")
    @JvmStatic
    fun setUsers(recyclerView : RecyclerView, userList : List<DummyUserInfo>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as DummyAdapter<*>) { userList?.let{setUsers(it)}}
    }
}