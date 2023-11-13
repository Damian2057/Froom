package com.froom.items.service

import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import org.springframework.stereotype.Service

@Service
class CategoryService {

    fun getCategories(): List<CategoryType> {
        return CategoryType.values().toList()
    }

    fun getBodyParts(): List<BodyPart> {
        return BodyPart.values().toList()
    }
}