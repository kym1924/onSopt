package com.kimym.onsopt.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.BR
import com.kimym.onsopt.R
import com.kimym.onsopt.data.model.Document
import com.kimym.onsopt.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.VHolder<ItemSearchBinding>>(){

    private var webs= emptyList<Document>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<ItemSearchBinding>(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))

    override fun getItemCount() = webs.size

    override fun onBindViewHolder(holder : VHolder<ItemSearchBinding>, position: Int) {
        holder.bind(webs[position])
    }

    fun setResults(webs : List<Document>) {
        this.webs = webs
        notifyDataSetChanged()
    }

    inner class VHolder<ItemKakaoSearchBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding : ItemSearchBinding = DataBindingUtil.bind(itemView)!!

        fun bind(document : Document) {
            binding.setVariable(BR.document, document)
        }
    }
}