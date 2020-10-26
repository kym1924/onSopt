package com.kimym.onsopt.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.R
import com.kimym.onsopt.recycler.detail.DetailActivity
import com.kimym.onsopt.room.User
import com.kimym.onsopt.util.startActivityWithUser


class RecyclerAdapter(private val context : Context) : RecyclerView.Adapter<RecyclerAdapter.VHolder>(){

    var users = emptyList<User>()
    var layoutItem = R.layout.item_recycler_linear

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : VHolder {
        val view = LayoutInflater.from(context).inflate(layoutItem, parent,false)
        return VHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder : VHolder, position : Int) {
        holder.id.text = users[position].id
        holder.name.text = users[position].name

        holder.itemView.setOnClickListener{
            context.startActivityWithUser<DetailActivity>(users[position])
        }
    }

    internal fun setUsers(users : List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    internal fun setLayout(layoutItem : Int) {
        this.layoutItem = layoutItem
    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.tv_user_id)
        val name = itemView.findViewById<TextView>(R.id.tv_user_name)
    }
}