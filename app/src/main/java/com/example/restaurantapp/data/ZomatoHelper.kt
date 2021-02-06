package com.example.restaurantapp.data

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantReviewNetwork

interface ZomatoHelper {
    suspend fun searchLocations(query : String) : List<LocationNetwork>
    suspend fun getRestaurants(options : Map<String, String?>) : List<RestaurantNetwork>
    suspend fun getRestaurantDetails(restaurantID : Int) : RestaurantDetailsNetwork
    suspend fun getRestaurantReviews(restaurantID: Int) : List<RestaurantReviewNetwork>
}