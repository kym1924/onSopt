package com.kimym.onsopt.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimym.onsopt.recycler.RecyclerViewModel


fun recyclerItemTouchHelper(recyclerView : RecyclerView, viewModel : RecyclerViewModel) {
    val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback((ItemTouchHelper.UP or ItemTouchHelper.DOWN), ItemTouchHelper.LEFT) {

            val adapter = recyclerView.adapter!!

            override fun onMove(
                recyclerView : RecyclerView,
                from : ViewHolder,
                to : ViewHolder
            ) : Boolean {
                return false
            }

            override fun onSwiped(viewHolder : ViewHolder, swipeDir : Int) {
                val swipedPosition = viewHolder.adapterPosition
                viewModel.delete(swipedPosition)
                adapter.notifyItemRemoved(swipedPosition)
            }
        })
    itemTouchHelper.attachToRecyclerView(recyclerView)
}
