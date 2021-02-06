package com.example.restaurantapp.framework

import com.example.restaurantapp.PathPlaceholder
import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantReviewNetwork

interface ZomatoRetrofit {
    suspend fun searchLocations(@PathPlaceholder("user-key") apiKey : String,
                                @PathPlaceholder("")query : String) : List<LocationNetwork>
    suspend fun getRestaurants(apiKey : String, options :  Map<String, String?>) : List<RestaurantNetwork>
    suspend fun getRestaurantDetails(@PathPlaceholder("user-key") apiKey: String,
                                     @PathPlaceholder("res_id") restaurantID : Int) : RestaurantDetailsNetwork
    suspend fun getRestaurantReviews(@PathPlaceholder("user-key") apiKey: String,
                                     @PathPlaceholder("res_id") restaurantID: Int,
                                     //TODO Note max is 5
                                     @PathPlaceholder("count") maxCount : Int? = 3) : List<RestaurantReviewNetwork>
}