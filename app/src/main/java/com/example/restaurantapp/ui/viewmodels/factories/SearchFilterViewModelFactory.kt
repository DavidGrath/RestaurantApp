package com.example.restaurantapp.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.data.RestaurantSearchHelper
import com.example.restaurantapp.ui.viewmodels.SearchFilterViewModel

class SearchFilterViewModelFactory(val helper : RestaurantSearchHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchFilterViewModel(helper) as T
    }
}