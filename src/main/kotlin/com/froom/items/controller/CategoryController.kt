package com.froom.items.controller

import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.Type
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
    fun getCategory(): ResponseEntity<Type> {
        return ResponseEntity<Type>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("part")
    fun getCategoryPart(): ResponseEntity<BodyPart> {
        return ResponseEntity<BodyPart>(null,
            org.springframework.http.HttpStatus.OK)
    }

}