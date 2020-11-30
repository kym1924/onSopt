package com.kimym.onsopt.ui.dummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.BR
import com.kimym.onsopt.R
import com.kimym.onsopt.data.model.DummyDiffUtil
import com.kimym.onsopt.data.model.DummyUserInfo

class DummyAdapter<B : ViewDataBinding> : RecyclerView.Adapter<DummyAdapter<B>.VHolder<B>>(){

    private var users = emptyList<DummyUserInfo>()
    private var layoutItem = R.layout.item_dummy_linear

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B>(LayoutInflater.from(parent.context).inflate(layoutItem, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: VHolder<B>, position: Int) {
        holder.bind(users[position])
    }

    fun setUsers(newList : List<DummyUserInfo>) {
        val diffUtilCallBack = DummyDiffUtil(users, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        this.users = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun setLayout(layoutItem : Int){
        this.layoutItem = layoutItem
    }

    inner class VHolder<B : ViewDataBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(dummy : DummyUserInfo) {
            binding.setVariable(BR.dummyUser, dummy)
        }
    }
}