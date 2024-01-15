package com.froom.util.retrofit.category

import com.froom.items.model.domain.CategoryType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

@Component
class CategoryAdapter(environment: Environment) {

    val logger: Logger = LoggerFactory.getLogger(CategoryAdapter::class.java)
    val categoryUrl: String = environment.getRequiredProperty("CATEGORY_URL")
    val categoryApiKey: String = environment.getRequiredProperty("CATEGORY_API_HEADER_KEY")
    val categoryApiSecret: String = environment.getRequiredProperty("CATEGORY_SECRET_API_KEY")

    fun getCategory(file: MultipartFile): CategoryType {
        val requestFile = file.bytes.toRequestBody("multipart/form-data".toMediaTypeOrNull(), 0)
        val filePart = MultipartBody.Part.createFormData("file", file.originalFilename, requestFile)
        val response = createApi().predict(filePart).execute()

        return if (!response.isSuccessful) {
            logger.error("Error while getting category")
            CategoryType.UNKNOWN
        } else {
            logger.info("Category: ${response.body()?.category}")
            CategoryType.valueOf(response.body()?.category?.uppercase() ?: "UNKNOWN")
        }
    }

    private fun createApi(): CategoryAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(categoryUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val requestBuilder: Request = chain.request().newBuilder()
                            .addHeader(HttpHeaders.ACCEPT, "application/json")
                            .addHeader(categoryApiKey, categoryApiSecret)
                            .build()
                        chain.proceed(requestBuilder)
                    }
                    .build()
            )
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        return retrofit.create(CategoryAPI::class.java)
    }
}