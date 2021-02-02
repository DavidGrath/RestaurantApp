package com.example.restaurantapp.data

import com.example.restaurantapp.domain.entities.ui.LocationUI

interface ZomatoHelper {
    suspend fun searchLocations(query : String) : List<LocationUI>
}