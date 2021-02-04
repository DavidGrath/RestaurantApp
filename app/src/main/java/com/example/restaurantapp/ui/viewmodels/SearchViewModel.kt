package com.example.restaurantapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.di.DaggerBasicAppGraph
import com.example.restaurantapp.domain.entities.ui.RestaurantUI
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val graph = DaggerBasicAppGraph.create()
    val repository = graph.restaurantRepository()

    private val _restaurantsList = MutableLiveData<ApiResult<List<RestaurantUI>>>(ApiResult.Pending(null))
    val restaurantsList : LiveData<ApiResult<List<RestaurantUI>>> = _restaurantsList

    fun searchRestaurants(locationId : Int, locationType : Int) {
        _restaurantsList.value = ApiResult.Pending(null)
        viewModelScope.launch {
            _restaurantsList.value = repository.searchRestaurants(locationId, locationType)
        }
    }
}