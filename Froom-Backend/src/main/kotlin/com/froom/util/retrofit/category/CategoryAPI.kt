package com.froom.util.retrofit.category

import com.froom.util.retrofit.category.model.CategoryResponseDto
import okhttp3.MultipartBody
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

@Component
interface CategoryAPI {

    @Multipart
    @POST("/predict")
    fun predict(@Part file: MultipartBody.Part): Call<CategoryResponseDto>
}