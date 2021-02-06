package com.example.restaurantapp

class Constants {
    companion object {
        const val PREFERENCES_LOCATION_ID = "location_id"
        const val PREFERENCES_LOCATION_NAME = "location_name"
        const val PREFERENCES_LOCATION_TYPE = "location_type"
        const val RESTAURANT_DETAILS_ID = "restaurant_details_id"
        const val API_KEY = "KEY"

        enum class LocationType(val locationType : String) {
            CITY("city"),
            SUB_ZONE("subzone"),
            ZONE("zone"),
            LANDMARK("landmark"),
            METRO("metro"),
            GROUP("group")
        }
        val LOCATION_MAP = mapOf(
                "city" to "City",
                "subzone" to "Sub Zone",
                "zone" to "Zone",
                "landmark" to "Landmark",
                "metro" to "Metro",
                "group" to "Group"
        )
        enum class SortType(val sortType : String) {
            COST("Cost"),
            RATING("rating"),
            REAL_DISTANCE("real_distance")
        }
        enum class OrderType(val orderType : String) {
            ASCENDING("asc"),
            DESCENDING("desc")
        }
    }
}