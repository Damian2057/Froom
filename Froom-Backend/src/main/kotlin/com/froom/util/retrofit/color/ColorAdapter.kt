package com.froom.util.retrofit.color

import com.froom.util.retrofit.BaseAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class ColorAdapter(environment: Environment) : BaseAdapter() {
    override val baseUrlKey: String = environment.getRequiredProperty("COLOR_URL")
    override val apiKey: String = environment.getRequiredProperty("COLOR_API_HEADER_KEY")
    override val apiSecretKey: String = environment.getRequiredProperty("COLOR_SECRET_API_KEY")

    fun getColor(file: MultipartFile): List<Int> {
        val requestFile = file.bytes.toRequestBody("multipart/form-data".toMediaTypeOrNull(), 0)
        val filePart = MultipartBody.Part.createFormData("file", file.originalFilename, requestFile)
        val response = createApi(baseUrlKey).create(ColorAPI::class.java).classifyColor(filePart).execute()

        return if (!response.isSuccessful) {
            logger.error("Error while getting color")
            listOf(0, 0, 0)
        } else {
            logger.info("Color: ${response.body()?.rgb}")
            response.body()?.rgb ?: listOf(0, 0, 0)
        }
    }
}