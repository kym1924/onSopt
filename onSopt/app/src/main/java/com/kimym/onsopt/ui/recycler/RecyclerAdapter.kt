package com.kimym.onsopt.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.R
import com.kimym.onsopt.room.User
import com.kimym.onsopt.ui.DetailActivity
import com.kimym.onsopt.util.startActivityWithUser


class RecyclerAdapter(private val context : Context) : RecyclerView.Adapter<RecyclerAdapter.VHolder>(){

    var users = emptyList<User>()
    var layoutItem = R.layout.item_recycler_linear

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : VHolder {
        return VHolder(LayoutInflater.from(context).inflate(layoutItem, parent,false))
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder : VHolder, position : Int) {
        holder.bind(users[position])
    }

    internal fun setUsers(users : List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    internal fun setLayout(layoutItem : Int) {
        this.layoutItem = layoutItem
    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.tv_user_id)
        val name: TextView = itemView.findViewById(R.id.tv_user_name)

        fun bind(user : User){
            id.text = user.id
            name.text = user.name

            itemView.setOnClickListener{
                context.startActivityWithUser<DetailActivity>(user)
            }
        }
    }
}
