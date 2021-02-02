package com.example.restaurantapp.data

import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.domain.entities.ui.LocationUI

class RestaurantRepository(private val helper : ZomatoHelper) {
    suspend fun searchLocations(query : String) : ApiResult<List<LocationUI>>{
        val result : ApiResult<List<LocationUI>> = try {
            ApiResult.Success(helper.searchLocations(query))
        } catch (e : Exception) {
            ApiResult.Failure(null, e.message)
        }
        return result
    }
}