package com.kimym.onsopt.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.BR
import com.kimym.onsopt.R
import com.kimym.onsopt.room.User
import com.kimym.onsopt.ui.DetailActivity
import com.kimym.onsopt.util.startActivityWithUser

class RecyclerAdapter<B : ViewDataBinding>(private val context : Context) : RecyclerView.Adapter<RecyclerAdapter<B>.VHolder<B>>(){

    private var users = emptyList<User>()
    private var layoutItem = R.layout.item_recycler_linear

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) =
        VHolder<B>(LayoutInflater.from(parent.context).inflate(layoutItem, parent,false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder : VHolder<B>, position : Int) {
        holder.bind(users[position])
    }

    internal fun setUsers(users : List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    internal fun setLayout(layoutItem : Int) {
        this.layoutItem = layoutItem
    }

    inner class VHolder<B : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(user: User) {
            binding.setVariable(BR.user, user)
            itemView.setOnClickListener{
                context.startActivityWithUser<DetailActivity>(user)
            }
        }
    }
}