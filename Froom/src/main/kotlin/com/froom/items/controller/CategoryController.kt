package com.froom.items.controller

import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.service.CategoryService
import org.springframework.http.HttpStatus
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
    fun getCategories(): ResponseEntity<List<CategoryType>> {
        return ResponseEntity<List<CategoryType>>(categoryService.getCategories(),
            HttpStatus.OK)
    }

    @GetMapping("part")
    fun getBodyParts(): ResponseEntity<List<BodyPart>> {
        return ResponseEntity<List<BodyPart>>(categoryService.getBodyParts(),
            HttpStatus.OK)
    }

}