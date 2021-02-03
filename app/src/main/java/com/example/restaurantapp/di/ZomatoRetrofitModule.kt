package com.example.restaurantapp.di

import com.example.restaurantapp.framework.ZomatoRetrofit
import com.example.restaurantapp.framework.ZomatoRetrofitInstance
import dagger.Module
import dagger.Provides

@Module
class ZomatoRetrofitModule {
    companion object {
        @Provides
        @JvmStatic
        fun zomatoRetrofitInstance(): ZomatoRetrofit {
            return ZomatoRetrofitInstance.getInstance()
        }
    }

}