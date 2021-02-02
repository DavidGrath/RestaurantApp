package com.example.restaurantapp.data

import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.framework.ZomatoHelperImpl
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking

class RestaurantRepositoryTest : TestCase() {

    val repository = RestaurantRepository(ZomatoHelperImpl())
    fun testSearchLocations() = runBlocking {
        val result = repository.searchLocations("Ded")
        assertTrue(result is ApiResult.Failure && result.message?:"" == "Unit Testing")
    }
}