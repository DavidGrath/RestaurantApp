package com.example.restaurantapp.framework

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork

interface ZomatoRetrofit {
    suspend fun searchLocations(apiKey : String, query : String) : List<LocationNetwork>
    suspend fun searchRestaurants(apiKey : String, locationID : Int, locationType : Int) : List<RestaurantNetwork>
    suspend fun getRestaurantDetails(apiKey: String, restaurantID : Int) : RestaurantDetailsNetwork
}