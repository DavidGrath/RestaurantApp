package com.example.restaurantapp

import com.example.restaurantapp.domain.entities.framework.network.LocationNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantDetailsNetwork
import com.example.restaurantapp.domain.entities.framework.network.RestaurantNetwork
import com.example.restaurantapp.domain.entities.ui.LocationUI
import com.example.restaurantapp.domain.entities.ui.RestaurantDetailsUI
import com.example.restaurantapp.domain.entities.ui.RestaurantUI

class MapperUtil {
    companion object {
        fun locationNetworkToLocationUI(location: LocationNetwork): LocationUI {
            return LocationUI(location.id, location.name, location.type)
        }
        fun restaurantNetworkToNetworkUI(restaurant : RestaurantNetwork) : RestaurantUI {
            with(restaurant) {
                return RestaurantUI(id, name, url, rating.aggregateRating, rating.ratingText,
                        rating.voteCount, address.address, address.locality, address.city, thumbUrl, photoUrl)
            }
        }
        fun restaurantDetailsNetworkToUI(restaurantDetails: RestaurantDetailsNetwork) : RestaurantDetailsUI {
            with(restaurantDetails) {
                return RestaurantDetailsUI()
            }
        }
    }

}