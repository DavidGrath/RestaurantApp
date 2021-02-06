package com.example.restaurantapp

class CommaSepList<T>(private val items : List<T>) {
    private val LENGTH = items.size
    override fun toString(): String {
        val strB = StringBuilder()
        for(i in 0..LENGTH-2) {
            strB.append(items[i].toString())
            strB.append(",")
        }
        strB.append(items[LENGTH-1])
        return strB.toString()
    }
}