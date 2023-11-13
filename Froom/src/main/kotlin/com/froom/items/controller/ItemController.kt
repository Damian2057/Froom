package com.froom.items.controller

import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.model.dto.ItemDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/item")
class ItemController {

    @GetMapping()
    fun getAllItems(): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("/{uuid}")
    fun getItemByUuid(@PathVariable uuid: String): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @PostMapping()
    fun createItem(): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @PutMapping("/{uuid}")
    fun updateItem(@PathVariable uuid: String): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @DeleteMapping("/{uuid}")
    fun deleteItem(@PathVariable uuid: String): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("/filter")
    fun getItemsByFilter(
        @RequestParam(required = false) type: CategoryType?,
        @RequestParam(required = false) bodyPart: BodyPart?,
    ): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(null,
            org.springframework.http.HttpStatus.OK)
    }

}