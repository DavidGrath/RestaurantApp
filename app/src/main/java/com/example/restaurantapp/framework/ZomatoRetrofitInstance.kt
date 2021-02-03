package com.example.restaurantapp.framework

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork

class ZomatoRetrofitInstance {
    companion object {
        private var INSTANCE : ZomatoRetrofit = object : ZomatoRetrofit {
            override suspend fun searchLocations(query: String): List<LocationNetwork> {
                return listOf(
                    LocationNetwork(1, "Lagos"),
                    LocationNetwork(2, "New York"),
                    LocationNetwork(3, "Paris")
                )
            }
        }
        fun getInstance() : ZomatoRetrofit {
            return INSTANCE
        }
    }

}