package com.example.restaurantapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.RecyclerviewRestaurantBinding
import com.example.restaurantapp.domain.entities.ui.RestaurantUI

class RestaurantRecyclerAdapter(var restaurantClickListener : RestaurantItemClickListener? = null) : ListAdapter<RestaurantUI, RestaurantRecyclerAdapter.RestaurantViewHolder>(diffUtil) {

    interface RestaurantItemClickListener {
        fun onRestaurantClicked(position: Int, restaurant: RestaurantUI)
    }
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RestaurantUI>() {
            override fun areItemsTheSame(oldItem: RestaurantUI, newItem: RestaurantUI): Boolean {
                return oldItem.equals(newItem)
            }

            override fun areContentsTheSame(oldItem: RestaurantUI, newItem: RestaurantUI): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = RecyclerviewRestaurantBinding.inflate(LayoutInflater.from(parent.context))
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        with(holder) {
            itemView.setOnClickListener {
                restaurantClickListener?.onRestaurantClicked(position, restaurant)
            }
            //TODO get stock photo for image
            restaurantName.text = restaurant.name
            restaurantAddress.text = restaurant.address
            rating.rating = restaurant.ratingAggregate
            if(restaurant.url != null) {
                restaurantLink.visibility = View.VISIBLE
                restaurantLink.text = restaurant.url
                //TODO Add external linking code, most probably a simple Intent
            }
        }
    }

    class RestaurantViewHolder(val binding : RecyclerviewRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        val thumbnail : ImageView = binding.imageviewRestaurantThumb
        val restaurantName : TextView = binding.textviewRestaurantName
        val restaurantAddress : TextView = binding.textviewRestaurantAddress
        val rating : RatingBar = binding.ratingBarRestaurant
        val restaurantLink : TextView = binding.textviewRestaurantLink
    }
}