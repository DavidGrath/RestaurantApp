package com.example.restaurantapp.data

import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantReviewNetwork
import com.example.restaurantapp.framework.ZomatoHelperImpl
import com.example.restaurantapp.framework.ZomatoRetrofit
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.runner.notification.Failure

class RestaurantRepositoryTest : TestCase() {

    lateinit var failingRetrofitInstance : ZomatoRetrofit
    lateinit var repository : RestaurantRepository

    override fun setUp() {
        super.setUp()
        failingRetrofitInstance = object : ZomatoRetrofit {
            override suspend fun searchLocations(apiKey: String, query: String): List<LocationNetwork> {
                throw Exception("Location Failure")
            }

            override suspend fun getRestaurants(apiKey: String, options: Map<String, String?>): List<RestaurantNetwork> {
                throw Exception("Get Restaurants Failure")
            }

            override suspend fun getRestaurantDetails(apiKey: String, restaurantID: Int): RestaurantDetailsNetwork {
                throw Exception("Get Restaurant Details Failure")
            }

            override suspend fun getRestaurantReviews(apiKey: String, restaurantID: Int, maxCount: Int?): List<RestaurantReviewNetwork> {
                throw Exception("Get Restaurant Reviews Failure")
            }
        }
        repository = RestaurantRepository(ZomatoHelperImpl(failingRetrofitInstance))
    }

    fun testSearchLocations() = runBlocking {
        val locationsResult = repository.searchLocations("Arbitrary Value")
        val restaurantListResult = repository.getRestaurants(emptyMap())

        assertTrue(locationsResult is ApiResult.Failure && locationsResult.message?:"" == "Location Failure")
        assertTrue(restaurantListResult is Failure && restaurantListResult.message == "Get Restaurants Failure")
    }


}