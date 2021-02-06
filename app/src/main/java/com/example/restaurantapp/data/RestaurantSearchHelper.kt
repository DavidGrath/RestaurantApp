package com.example.restaurantapp.data

import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions

interface RestaurantSearchHelper {
    fun getSortFilterOptions() : RestaurantSearchFilterOptions?
    fun setSortFilterOptions(options: RestaurantSearchFilterOptions)
}