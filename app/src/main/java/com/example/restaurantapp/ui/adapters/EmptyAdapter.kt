package com.example.restaurantapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.RecyclerviewEmptyBinding

class EmptyAdapter(private val title : String, private val description : String) : RecyclerView.Adapter<EmptyAdapter.EmptyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        val binding = RecyclerviewEmptyBinding.inflate(LayoutInflater.from(parent.context))
        return EmptyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        with(holder) {
            emptyTitle.text = title
            emptyText.text = description
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    class EmptyViewHolder(private val binding : RecyclerviewEmptyBinding) : RecyclerView.ViewHolder(binding.root) {
        val emptyTitle : TextView = binding.textviewEmptyRecyclerTitle
        val emptyText : TextView = binding.textviewEmptyRecyclerText
    }
}