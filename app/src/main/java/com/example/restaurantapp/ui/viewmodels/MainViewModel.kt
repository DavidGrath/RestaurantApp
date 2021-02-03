package com.example.restaurantapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.data.RestaurantRepository
import com.example.restaurantapp.di.DaggerBasicAppGraph
import com.example.restaurantapp.domain.entities.ui.LocationUI
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repository : RestaurantRepository
    init {
        val appGraph = DaggerBasicAppGraph.create()
        repository = appGraph.restaurantRepository()
    }

    private val _searchResult = MutableLiveData<ApiResult<List<LocationUI>>>()
    val searchResult : LiveData<ApiResult<List<LocationUI>>> = _searchResult

    fun searchLocations(query : String) {
        _searchResult.value = ApiResult.Pending(null)
        viewModelScope.launch {
            _searchResult.value = repository.searchLocations(query)
        }
    }
}