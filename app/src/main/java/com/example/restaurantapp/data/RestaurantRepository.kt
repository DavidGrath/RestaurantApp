package com.example.restaurantapp.data

import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.MapperUtil
import com.example.restaurantapp.di.CustomAppScope
import com.example.restaurantapp.domain.entities.ui.LocationUI
import com.example.restaurantapp.domain.entities.ui.RestaurantUI
import javax.inject.Inject

@CustomAppScope
class RestaurantRepository @Inject constructor(private val helper : ZomatoHelper) {
    suspend fun searchLocations(query : String) : ApiResult<List<LocationUI>>{
        val result : ApiResult<List<LocationUI>> = try {
            val search = helper.searchLocations(query)
            ApiResult.Success(search.map { MapperUtil.locationNetworkToLocationUI(it) })
        } catch (e : Exception) {
            ApiResult.Failure(null, e.message)
        }
        return result
    }
    suspend fun getRestaurants(options : Map<String, String?>) : ApiResult<List<RestaurantUI>>{
        val result : ApiResult<List<RestaurantUI>> = try {
            val restaurants = helper.getRestaurants(options)
            ApiResult.Success(restaurants.map { MapperUtil.restaurantNetworkToNetworkUI(it) })
        } catch (e : Exception) {
            ApiResult.Failure(null, e.message)
        }
        return result
    }
}