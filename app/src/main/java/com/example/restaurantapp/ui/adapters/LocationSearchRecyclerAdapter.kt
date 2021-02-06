package com.example.restaurantapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.Constants
import com.example.restaurantapp.Constants.Companion.LOCATION_MAP
import com.example.restaurantapp.databinding.RecyclerviewLocationSearchBinding
import com.example.restaurantapp.domain.entities.ui.LocationUI

class LocationSearchRecyclerAdapter(var clickListener : SearchItemClickListener? = null) :
        ListAdapter<LocationUI, LocationSearchRecyclerAdapter.LocationSearchViewHolder>(diffUtil){
    //TODO convert all functional interfaces to Single-Abstract-Method types by putting "fun" at the back of "interface"
    interface SearchItemClickListener {
        fun onLocationClick(position: Int, item : LocationUI)
    }
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LocationUI>() {
            override fun areItemsTheSame(oldItem: LocationUI, newItem: LocationUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: LocationUI, newItem: LocationUI): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.type == newItem.type
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationSearchViewHolder {
        val binding = RecyclerviewLocationSearchBinding.inflate(LayoutInflater.from(parent.context))
        return LocationSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationSearchViewHolder, position: Int) {
        val location = getItem(position)
        with(holder) {
            locationName.text = location.name
            locationType.text = LOCATION_MAP.get(location.type)?: Constants.Companion.LocationType.CITY.locationType
            itemView.setOnClickListener {
                    clickListener?.onLocationClick(position, location)
            }
        }
    }

    class LocationSearchViewHolder(private val binding : RecyclerviewLocationSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val locationName : TextView = binding.textviewLocationsearchTitle
        val locationType : TextView = binding.textviewLocationsearchType
    }
}