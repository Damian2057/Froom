package com.froom.util.retrofit.category

import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

@Component
interface CategoryAPI {

    @GET("/predict")
    fun predict(@Body body: String): Call<Object>
}