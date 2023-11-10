package com.froom.items.controller

import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController(
    val categoryService: CategoryService
) {

    @GetMapping()
    fun getCategory(): ResponseEntity<List<CategoryType>> {
        return ResponseEntity<List<CategoryType>>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("part")
    fun getCategoryPart(): ResponseEntity<List<BodyPart>> {
        return ResponseEntity<List<BodyPart>>(null,
            org.springframework.http.HttpStatus.OK)
    }

}