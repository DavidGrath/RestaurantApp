package com.example.restaurantapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.data.RestaurantSearchHelper
import com.example.restaurantapp.di.DaggerBasicAppGraph
import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions
import com.example.restaurantapp.domain.entities.ui.RestaurantUI
import kotlinx.coroutines.launch

class SearchViewModel(private val helper : RestaurantSearchHelper, defaultOptions: RestaurantSearchFilterOptions) : ViewModel() {

    init {
        helper.setSortFilterOptions(defaultOptions)
    }
    val graph = DaggerBasicAppGraph.create()
    val repository = graph.restaurantRepository()

    fun setOptions(options: RestaurantSearchFilterOptions) = helper.setSortFilterOptions(options)
    fun getOptions() = helper.getSortFilterOptions()

    private val _restaurantsList = MutableLiveData<ApiResult<List<RestaurantUI>>>(ApiResult.Pending(null))
    val restaurantsList : LiveData<ApiResult<List<RestaurantUI>>> = _restaurantsList

    fun getRestaurants() {
        _restaurantsList.value = ApiResult.Pending(null)
        viewModelScope.launch {
            _restaurantsList.value = repository.getRestaurants(getOptions()!!.toQueryOptions())
        }
    }
}