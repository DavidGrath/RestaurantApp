package com.example.restaurantapp.domain.entities.framework.network

//TODO Revisit Zomato docs to get Retrofit annotation labels
data class RestaurantNetwork(
        val id : Int,
        val name : String,
        val url : String?,
        val rating : UserRating,
        val address : RestaurantLocation,
        val thumbUrl : String,
        val photoUrl : String
) {
    data class UserRating(
            val aggregateRating : Float,
            val ratingText : String,
            val voteCount : Int
    )
    data class RestaurantLocation(
            val address : String,
            val locality : String,
            val city : String
    )
}