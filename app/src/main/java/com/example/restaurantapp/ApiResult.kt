package com.example.restaurantapp

sealed class ApiResult<T>(val data : T?) {
    class Success<T>(data: T?) : ApiResult<T>(data)
    class Pending<T>(data: T?) : ApiResult<T>(data)
    class Failure<T>(data: T?, val message : String? = null) : ApiResult<T>(data)
}