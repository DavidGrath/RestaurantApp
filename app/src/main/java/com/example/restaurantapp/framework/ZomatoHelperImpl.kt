package com.example.restaurantapp.framework

import com.example.restaurantapp.data.ZomatoHelper
import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import javax.inject.Inject

class ZomatoHelperImpl @Inject constructor(private val retrofit: ZomatoRetrofit) : ZomatoHelper {
    override suspend fun searchLocations(query: String): List<LocationNetwork> {
        return retrofit.searchLocations(query)
    }
}