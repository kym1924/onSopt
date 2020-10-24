package com.kimym.onsopt.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimym.onsopt.recycler.RecyclerViewModel
import com.kimym.onsopt.room.User
import java.util.*


fun recyclerItemTouchHelper(recyclerView : RecyclerView, viewModel : RecyclerViewModel, userList : ArrayList<User>) {
    val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback((ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END), ItemTouchHelper.LEFT) {

            val adapter = recyclerView.adapter!!

            override fun onMove(
                recyclerView : RecyclerView,
                from : ViewHolder,
                to : ViewHolder
            ) : Boolean {
                val fromPosition = from.adapterPosition
                val toPosition = to.adapterPosition
                if (fromPosition < toPosition) {
                    for (i in fromPosition until toPosition) {
                        Collections.swap(userList, i, i+1)
                        val fromIdx = userList[i].idx
                        val toIdx = userList[i+1].idx
                        userList[i].idx = toIdx
                        userList[i+1].idx = fromIdx
                    }
                } else {
                    for (i in fromPosition downTo toPosition+1){
                        Collections.swap(userList, i, i-1)
                        val fromIdx = userList[i].idx
                        val toIdx = userList[i-1].idx
                        userList[i].idx = toIdx
                        userList[i-1].idx = fromIdx
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition)
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, swipeDir: Int) {
                val swipedPosition = viewHolder.adapterPosition
                viewModel.delete(swipedPosition)
                adapter.notifyItemRemoved(swipedPosition)
            }
        })
    itemTouchHelper.attachToRecyclerView(recyclerView)
}