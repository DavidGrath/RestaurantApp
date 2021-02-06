package com.example.restaurantapp.domain.entities.framework.network

import com.example.restaurantapp.GsonPlaceholder

//TODO Add entity details
data class RestaurantDetailsNetwork(
        @GsonPlaceholder("id")
        val id : Int,
        @GsonPlaceholder("name")
        val name : String,
        @GsonPlaceholder("url")
        val url : String? = null,
        @GsonPlaceholder("location")
        val location : RestaurantDetailsLocation? = null,
        @GsonPlaceholder("average_cost_for_two")
        val averageCostForTwo : Int? = null,
        @GsonPlaceholder("price_range")
        //It's in the range 1(pocket friendly) to 4(costly)
        val priceRange : Int? = null,
        @GsonPlaceholder("currency")
        val currency : String? = null,
        @GsonPlaceholder("thumb")
        val thumbnail : String? = null,
        @GsonPlaceholder("featured_image")
        val featuredImage : String? = null,
        @GsonPlaceholder("user_rating")
        val userRating : RestaurantDetailsRating? = null,
        @GsonPlaceholder("has_online_delivery")
        val hasOnlineDelivery : Boolean? = null,
        @GsonPlaceholder("cuisines")
        val cuisines : String? = null
) {
    data class RestaurantDetailsLocation(
            @GsonPlaceholder("address")
            val address : String? = null,
            @GsonPlaceholder("locality")
            val locality : String? = null,
            @GsonPlaceholder("city")
            val city : String? = null
    )
    data class RestaurantDetailsRating(
            @GsonPlaceholder("aggregate_rating")
            val aggregate : Float? = null,
            @GsonPlaceholder("rating_text")
            val text : String? = null,
            @GsonPlaceholder("votes")
            val voteCount : Int? = null
    )
}