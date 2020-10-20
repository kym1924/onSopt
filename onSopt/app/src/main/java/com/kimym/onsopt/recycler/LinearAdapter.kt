package com.kimym.onsopt.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt27.room.User
import com.kimym.onsopt.R

class LinearAdapter(private val context : Context) : RecyclerView.Adapter<LinearAdapter.VHolder>(){
    var users = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : VHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_linear, parent,false)
        return VHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: LinearAdapter.VHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.tv_user_id)
        val name = itemView.findViewById<TextView>(R.id.tv_user_name)

        fun bind(user : User){
            id.text = user.id
            name.text = user.name
        }
    }
}