package com.kimym.onsopt.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.BR
import com.kimym.onsopt.R
import com.kimym.onsopt.data.model.Document
import com.kimym.onsopt.databinding.ItemKakaoSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.VHolder<ItemKakaoSearchBinding>>(){

    private var webs= emptyList<Document>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<ItemKakaoSearchBinding>(LayoutInflater.from(parent.context).inflate(R.layout.item_kakao_search, parent, false))

    override fun getItemCount() = webs.size

    override fun onBindViewHolder(holder : VHolder<ItemKakaoSearchBinding>, position: Int) {
        holder.bind(webs[position])
    }

    fun setResults(webs : List<Document>) {
        this.webs = webs
        notifyDataSetChanged()
    }

    inner class VHolder<ItemKakaoSearchBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding : com.kimym.onsopt.databinding.ItemKakaoSearchBinding = DataBindingUtil.bind(itemView)!!

        fun bind(document : Document) {
            binding.setVariable(BR.document, document)
        }
    }
}