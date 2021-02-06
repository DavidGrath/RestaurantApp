package com.example.restaurantapp.domain.entities

import com.example.restaurantapp.Constants
import junit.framework.TestCase

class RestaurantSearchFilterOptionsTest : TestCase() {

    fun testToQueryOptions() {
        val searchOptions = RestaurantSearchFilterOptions(23, Constants.Companion.LocationType.CITY.locationType)
        searchOptions.count = 20
        searchOptions.orderBy = Constants.Companion.OrderType.ASCENDING

        val queryOptions = searchOptions.toQueryOptions()

        assertEquals("23", queryOptions.get("location"))
        assertEquals("city", queryOptions.get("entity_type"))
        assertEquals("20", queryOptions.get("count"))
        assertEquals("asc", queryOptions.get("sort"))
    }
}