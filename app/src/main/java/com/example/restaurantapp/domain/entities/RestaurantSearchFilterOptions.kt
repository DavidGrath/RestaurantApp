package com.example.restaurantapp.domain.entities

import com.example.restaurantapp.CommaSepList
import com.example.restaurantapp.Constants


data class RestaurantSearchFilterOptions(
        var locationID : Int? = null,
        var locationType : String? = null,
        var startOffset : Int? = null,
        var count : Int? = null,
        var latitude : Double? = null,
        var longitude : Double? = null,
        var radius : Double? = null,
        var cuisines : CommaSepList<Int>? = null,
        var establishmentType : String? = null,
        var category : String? = null,
        var collectionID : String? = null,
        var sortBy : Constants.Companion.SortType? = null,
        var orderBy: Constants.Companion.OrderType? = null
) {
    fun toQueryOptions() : Map<String, String?> {
        val map = HashMap<String, String?>().apply {
            put("entity_id", locationID?.toString())
            put("entity_type", locationType)
            put("start", startOffset?.toString())
            put("count", count?.toString())
            put("lat", latitude?.toString())
            put("lon", longitude?.toString())
            put("radius", radius?.toString())
            put("cuisines", cuisines.toString())
            put("establishment_type", establishmentType)
            put("collection_id", collectionID)
            put("category", category)
            put("sort", sortBy?.sortType)
            put("order", orderBy?.orderType)
        }
        return map
    }
}