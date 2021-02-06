package com.example.restaurantapp.ui

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.restaurantapp.data.RestaurantSearchHelper
import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions

class SearchService : Service() {

    var binder : SearchBinder? = null

    override fun onBind(intent: Intent): IBinder? {
        if(binder == null) {
            binder = SearchBinder()
        }
        return binder
    }

    class SearchBinder : Binder(), RestaurantSearchHelper {

        var options : RestaurantSearchFilterOptions? = null

        override fun getSortFilterOptions(): RestaurantSearchFilterOptions? {
            return options
        }

        override fun setSortFilterOptions(options: RestaurantSearchFilterOptions) {
            this.options = options
        }
    }
}