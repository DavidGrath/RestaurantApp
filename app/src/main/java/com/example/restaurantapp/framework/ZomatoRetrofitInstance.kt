package com.example.restaurantapp.framework

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork

class ZomatoRetrofitInstance {
    companion object {
        private var INSTANCE : ZomatoRetrofit = object : ZomatoRetrofit {
            override suspend fun searchLocations(apiKey : String, query: String): List<LocationNetwork> {
                return listOf(
                    LocationNetwork(1, "Lagos", 2),
                    LocationNetwork(2, "New York", 1),
                    LocationNetwork(3, "Paris", 4)
                )
            }

            override suspend fun searchRestaurants(apiKey: String, locationID: Int, locationType: Int): List<RestaurantNetwork> {
                return emptyList()
            }

            override suspend fun getRestaurantDetails(apiKey: String, restaurantID: Int): RestaurantDetailsNetwork {
                return RestaurantDetailsNetwork()
            }

        }
        fun getInstance() : ZomatoRetrofit {
            return INSTANCE
        }
    }

}