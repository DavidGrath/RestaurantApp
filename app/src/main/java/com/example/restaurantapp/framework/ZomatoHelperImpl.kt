package com.example.restaurantapp.framework

import com.example.restaurantapp.Constants
import com.example.restaurantapp.data.ZomatoHelper
import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantReviewNetwork
import javax.inject.Inject

class ZomatoHelperImpl @Inject constructor(private val retrofit: ZomatoRetrofit) : ZomatoHelper {

    //TODO Replace with dependency from AppGraph
    val apiKey : String = Constants.API_KEY

    override suspend fun searchLocations(query: String): List<LocationNetwork> {
        return retrofit.searchLocations(apiKey, query)
    }

    override suspend fun getRestaurants(options : Map<String, String?>): List<RestaurantNetwork> {
        return retrofit.getRestaurants(apiKey, options)
    }

    override suspend fun getRestaurantDetails(restaurantID: Int): RestaurantDetailsNetwork {
        return retrofit.getRestaurantDetails(apiKey, restaurantID)
    }

    override suspend fun getRestaurantReviews(restaurantID: Int): List<RestaurantReviewNetwork> {
        return retrofit.getRestaurantReviews(apiKey, restaurantID)
    }
}