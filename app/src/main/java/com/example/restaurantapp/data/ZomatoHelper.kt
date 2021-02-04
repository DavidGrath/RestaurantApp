package com.example.restaurantapp.data

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork

interface ZomatoHelper {
    suspend fun searchLocations(query : String) : List<LocationNetwork>
    suspend fun searchRestaurants(locationID : Int, locationType : Int) : List<RestaurantNetwork>
    suspend fun getRestaurantDetails(restaurantID : Int) : RestaurantDetailsNetwork
}