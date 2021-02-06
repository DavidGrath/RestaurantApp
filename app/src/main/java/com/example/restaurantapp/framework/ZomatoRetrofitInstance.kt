package com.example.restaurantapp.framework

import com.example.restaurantapp.Constants
import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantReviewNetwork

class ZomatoRetrofitInstance {
    companion object {
        private var INSTANCE : ZomatoRetrofit = object : ZomatoRetrofit {
            override suspend fun searchLocations(apiKey : String, query: String): List<LocationNetwork> {
                return listOf(
                    LocationNetwork(1, "Lagos", Constants.Companion.LocationType.CITY.locationType),
                    LocationNetwork(2, "New York", Constants.Companion.LocationType.LANDMARK.locationType),
                    LocationNetwork(3, "Paris", Constants.Companion.LocationType.GROUP.locationType)
                )
            }

            override suspend fun getRestaurants(apiKey: String, options : Map<String, String?>): List<RestaurantNetwork> {
                return emptyList()
            }

            override suspend fun getRestaurantDetails(apiKey: String, restaurantID: Int): RestaurantDetailsNetwork {
                return RestaurantDetailsNetwork(1, "Rest name")
            }

            override suspend fun getRestaurantReviews(apiKey: String, restaurantID: Int, maxCount: Int?): List<RestaurantReviewNetwork> {
                return emptyList()
            }
        }
        fun getInstance() : ZomatoRetrofit {
            return INSTANCE
        }
    }

}