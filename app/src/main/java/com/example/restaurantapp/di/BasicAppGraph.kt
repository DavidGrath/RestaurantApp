package com.example.restaurantapp.di

import com.example.restaurantapp.data.RestaurantRepository
import dagger.Component

@CustomAppScope
@Component(modules = [ZomatoRetrofitModule::class, ZomatoRetrofitModuleBinds::class])
interface BasicAppGraph {
    fun restaurantRepository() : RestaurantRepository
}