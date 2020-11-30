package com.kimym.onsopt.data.model

import androidx.recyclerview.widget.DiffUtil

class DummyDiffUtil(private val oldList : List<DummyUserInfo>, private val newList : List<DummyUserInfo>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id
}