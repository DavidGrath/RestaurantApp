package com.example.restaurantapp.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.data.RestaurantSearchHelper
import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions
import com.example.restaurantapp.ui.viewmodels.SearchViewModel

class SearchViewModelFactory(val helper : RestaurantSearchHelper, val defaultOptions: RestaurantSearchFilterOptions) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(helper, defaultOptions) as T
    }
}