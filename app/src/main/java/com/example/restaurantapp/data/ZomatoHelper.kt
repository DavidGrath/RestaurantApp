package com.example.restaurantapp.data

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork

interface ZomatoHelper {
    suspend fun searchLocations(query : String) : List<LocationNetwork>
}