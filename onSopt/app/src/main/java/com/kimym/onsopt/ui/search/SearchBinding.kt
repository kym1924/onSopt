package com.kimym.onsopt.ui.search

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.data.model.Document

object SearchBinding {
    @BindingAdapter("visibleResetButton")
    @JvmStatic
    fun visibleResetButton(view : View, keyword : String) {
        view.visibility = if (keyword.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    @BindingAdapter("setListItem")
    @JvmStatic
    fun setListItem(recyclerView : RecyclerView, webs : List<Document>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as SearchAdapter) { webs?.let{ setResults(it) } }
    }
}