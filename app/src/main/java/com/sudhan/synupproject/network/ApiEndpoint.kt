package com.sudhan.synupproject.network

import com.sudhan.synupproject.splash.model.ResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("19u0sf")
    fun getVariants(): Single<ResponseModel>
}