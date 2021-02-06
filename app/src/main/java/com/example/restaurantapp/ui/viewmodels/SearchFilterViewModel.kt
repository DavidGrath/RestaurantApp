package com.example.restaurantapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.data.RestaurantSearchHelper
import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions

class SearchFilterViewModel(val helper : RestaurantSearchHelper) : ViewModel() {
    private var filterOptions = helper.getSortFilterOptions()?: RestaurantSearchFilterOptions()
    //TODO Making this an extension function because there has to be a better way
    fun RestaurantSearchFilterOptions.clear() {
        startOffset = null
        count = null
        latitude = null
        longitude = null
        radius = null
        cuisines= null
        establishmentType = null
        category = null
        collectionID = null
        sortBy = null
        orderBy = null
    }
    fun setOptions(options : RestaurantSearchFilterOptions) {
        this.filterOptions = options
    }
    fun getOptions() = filterOptions
    fun clearOptions() = filterOptions.clear()
    fun updateHelper() {
        helper.setSortFilterOptions(filterOptions)
    }
}