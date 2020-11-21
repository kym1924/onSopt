package com.kimym.onsopt.ui.recycler

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.R
import com.kimym.onsopt.room.User

object RecyclerBinding {
    @BindingAdapter("setLayout")
    @JvmStatic
    fun setLayout(recyclerView : RecyclerView, layoutItem : Int) {
        if(layoutItem == R.layout.item_recycler_linear) recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        else recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
    }

    @BindingAdapter("setList")
    @JvmStatic
    fun setList(recyclerView : RecyclerView, userList : List<User>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as RecyclerAdapter<*>) { userList?.let{setUsers(it)}}
    }
}