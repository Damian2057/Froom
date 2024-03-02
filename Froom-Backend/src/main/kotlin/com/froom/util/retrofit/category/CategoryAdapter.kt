package com.froom.util.retrofit.category

import com.froom.items.model.domain.CategoryType
import com.froom.util.retrofit.BaseAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile


@Component
class CategoryAdapter(environment: Environment) : BaseAdapter() {
    override val baseUrlKey: String = environment.getRequiredProperty("CATEGORY_URL")
    override val apiKey: String = environment.getRequiredProperty("CATEGORY_API_HEADER_KEY")
    override val apiSecretKey: String = environment.getRequiredProperty("CATEGORY_SECRET_API_KEY")

    fun getCategory(file: MultipartFile): CategoryType {
        val requestFile = file.bytes.toRequestBody("multipart/form-data".toMediaTypeOrNull(), 0)
        val filePart = MultipartBody.Part.createFormData("file", file.originalFilename, requestFile)
        val response = createApi(baseUrlKey).create(CategoryAPI::class.java).predict(filePart).execute()

        return if (!response.isSuccessful) {
            logger.error("Error while getting category")
            CategoryType.UNKNOWN
        } else {
            logger.info("Category: ${response.body()?.category}")
            CategoryType.valueOf(response.body()?.category?.uppercase() ?: "UNKNOWN")
        }
    }
}