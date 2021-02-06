package com.example.restaurantapp

import junit.framework.TestCase

class CommaSepListTest : TestCase() {

    fun testTestToString() {
        val nums = CommaSepList(arrayListOf(100, 200, 400, 30))
        val singleNum = CommaSepList(arrayListOf(23.5))
        assertEquals("100,200,400,30", nums.toString())
        assertEquals("235", singleNum.toString())
    }
}