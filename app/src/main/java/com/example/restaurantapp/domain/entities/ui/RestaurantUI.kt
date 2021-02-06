package com.example.restaurantapp.domain.entities.ui

data class RestaurantUI(
        val id : Int,
        val name : String,
        val url : String?,
        val ratingAggregate : Float,
        val ratingText : String,
        val ratingVoteCount : Int,
        val address : String,
        val addressLocality : String,
        val addressCity : String,
        val thumbUrl : String,
        val photoUrl : String,
        var bookmarked : Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RestaurantUI

        if (id != other.id) return false
        if (name != other.name) return false
        if (url != other.url) return false
        if (ratingAggregate != other.ratingAggregate) return false
        if (ratingText != other.ratingText) return false
        if (ratingVoteCount != other.ratingVoteCount) return false
        if (address != other.address) return false
        if (addressLocality != other.addressLocality) return false
        if (addressCity != other.addressCity) return false
        if (thumbUrl != other.thumbUrl) return false
        if (photoUrl != other.photoUrl) return false
        if (!(bookmarked and other.bookmarked)) return false

        return true
    }
}