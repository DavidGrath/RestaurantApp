package com.example.restaurantapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R

class ProgressAdapter : RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_loading, parent, false)
        return ProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    class ProgressViewHolder(val item : View) : RecyclerView.ViewHolder(item)
}