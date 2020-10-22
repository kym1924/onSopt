package com.kimym.onsopt.recycler.linear

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimym.onsopt.R
import com.kimym.onsopt.room.User

class LinearAdapter(private val context : Context) : RecyclerView.Adapter<LinearAdapter.VHolder>(){
    var users = emptyList<User>()

    private lateinit var itemClickListener : ItemClickListener

    interface ItemClickListener {
        fun onClick(view : View, user : User)
    }

    fun setItemClickListener(itemClickListener : ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : VHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_linear, parent,false)
        return VHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder : VHolder, position : Int) {
        holder.id.text = users[position].id
        holder.name.text = users[position].name
        holder.itemView.setOnClickListener { itemClickListener.onClick(it, users[position]) }
    }

    internal fun setUsers(users : List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.tv_user_id)
        val name = itemView.findViewById<TextView>(R.id.tv_user_name)
    }
}