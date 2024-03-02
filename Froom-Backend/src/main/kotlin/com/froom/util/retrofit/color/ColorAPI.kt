package com.froom.util.retrofit.color

import com.froom.util.retrofit.color.model.ColorResponseDto
import okhttp3.MultipartBody
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

@Component
interface ColorAPI {

    @Multipart
    @POST("/classify_color")
    fun classifyColor(@Part file: MultipartBody.Part): Call<ColorResponseDto>
}