package com.kimym.onsopt.ui.recycler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kimym.onsopt.R
import com.kimym.onsopt.databinding.FragmentRecyclerBinding
import com.kimym.onsopt.databinding.ItemRecyclerLinearBinding
import com.kimym.onsopt.room.User
import com.kimym.onsopt.room.UserDatabase
import com.kimym.onsopt.util.itemTouchHelper

class RecyclerFragment : Fragment() {

    private val recyclerViewModel : RecyclerViewModel by activityViewModels()
    private var userList = mutableListOf<User>()
    private lateinit var adapter : RecyclerAdapter<ItemRecyclerLinearBinding>
    lateinit var binding : FragmentRecyclerBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        adapter = RecyclerAdapter(context)

        val userDao = UserDatabase.getDatabase(context).userDao()
        recyclerViewModel.init(userDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false)
        binding.recyclerViewModel = recyclerViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        recyclerViewModel.allUsers.observe(this, Observer { allUsers ->
            allUsers?.let { recyclerViewModel.resetList(userList, allUsers) } })

        recyclerViewModel.layoutItem.observe(this, Observer { layoutItem ->
            layoutItem?.let {
                adapter.setLayout(layoutItem)
                binding.rvRecycler.adapter = adapter
            }
        })
    }

    override fun onResume(){
        super.onResume()
        binding.rvRecycler.itemTouchHelper(recyclerViewModel, userList)
    }
}