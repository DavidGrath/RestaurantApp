package com.example.restaurantapp.framework

import com.example.restaurantapp.data.ZomatoHelper
import com.example.restaurantapp.domain.entities.ui.LocationUI

class ZomatoHelperImpl : ZomatoHelper {
    override suspend fun searchLocations(query: String): List<LocationUI> {
        throw Exception("Unit Testing")
    }
}