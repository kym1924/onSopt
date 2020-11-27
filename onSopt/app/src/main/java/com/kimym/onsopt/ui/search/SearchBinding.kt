package com.kimym.onsopt.ui.search

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
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

    @BindingAdapter("setDateText")
    @JvmStatic
    fun setDateText(textView : TextView, dateString : String) {
        textView.text = dateString.split("T")[0]
    }

    @BindingAdapter("setTitleText")
    @JvmStatic
    fun setTitleText(textView : TextView, titleString : String) {
        textView.text = Html.fromHtml(titleString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("setPageText")
    @JvmStatic
    fun setPageText(textView : TextView, page : Int) {
        textView.text = "Page "+(page-1).toString()+" / 50"
    }
}