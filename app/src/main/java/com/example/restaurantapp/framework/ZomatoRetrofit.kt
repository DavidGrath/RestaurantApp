package com.example.restaurantapp.framework

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork

interface ZomatoRetrofit {
    suspend fun searchLocations(query : String) : List<LocationNetwork>
}