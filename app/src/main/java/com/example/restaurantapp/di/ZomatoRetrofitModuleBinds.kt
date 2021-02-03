package com.example.restaurantapp.di

import com.example.restaurantapp.data.ZomatoHelper
import com.example.restaurantapp.framework.ZomatoHelperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ZomatoRetrofitModuleBinds {
    @Binds
    abstract fun zomatoHelperInstance(zomatoHelper: ZomatoHelperImpl) : ZomatoHelper
}