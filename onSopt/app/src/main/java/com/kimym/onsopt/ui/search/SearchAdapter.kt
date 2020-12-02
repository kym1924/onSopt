package com.kimym.onsopt.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.BR
import com.kimym.onsopt.R
import com.kimym.onsopt.data.model.Document
import com.kimym.onsopt.data.model.SearchDiffUtil
import com.kimym.onsopt.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.VHolder>(){

    private var webs= emptyList<Document>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))

    override fun getItemCount() = webs.size

    override fun onBindViewHolder(holder : VHolder, position: Int) {
        holder.bind(webs[position])
        holder.itemView.setOnClickListener{ urlClickListener.onClick(it, webs[position])}
    }

    fun setResults(newList : List<Document>) {
        val diffUtilCallBack = SearchDiffUtil(webs, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        this.webs = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding : ItemSearchBinding = DataBindingUtil.bind(itemView)!!

        fun bind(document : Document) {
            binding.setVariable(BR.document, document)
        }
    }

    private lateinit var urlClickListener : UrlClickListener

    interface UrlClickListener {
        fun onClick(view : View, document : Document)
    }

    fun urlClickListener(urlClickListener : UrlClickListener){
        this.urlClickListener = urlClickListener
    }
}