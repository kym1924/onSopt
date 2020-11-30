package com.kimym.onsopt.data.model

import androidx.recyclerview.widget.DiffUtil

class SearchDiffUtil(private val oldList : List<Document>, private val newList : List<Document>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].title == newList[newItemPosition].title
}