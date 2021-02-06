package com.example.restaurantapp.domain.entities.framework.network

import com.example.restaurantapp.GsonPlaceholder

data class RestaurantReviewNetwork(
        @GsonPlaceholder("id")
        val id : Int,
        @GsonPlaceholder("rating")
        val rating : Int,
        @GsonPlaceholder("review_text")
        val reviewText : String,
        @GsonPlaceholder("review_time_friendly")
        val friendlyReviewTime : String,
        @GsonPlaceholder("timestamp")
        val timestamp : Long,
        @GsonPlaceholder("likes")
        val likes : Int,
        @GsonPlaceholder("user")
        val user : RestaurantReviewUser
) {
    data class RestaurantReviewUser(
            @GsonPlaceholder("name")
            val name : String,
            @GsonPlaceholder("profile_image")
            val profileImage : String?
    )
}