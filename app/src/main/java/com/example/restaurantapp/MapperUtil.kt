package com.example.restaurantapp

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.ui.LocationUI

class MapperUtil {
    companion object {
        fun locationNetworkToLocationUI(location: LocationNetwork): LocationUI {
            return LocationUI(location.id, location.name)
        }
    }

}