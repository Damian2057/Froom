package com.froom.items.controller

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
    fun getAllItems(): String {
        return "All items"
    }

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: String): String {
        return "Item by id" + id
    }

    @PostMapping()
    fun createItem(): String {
        return "Item created"
    }

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: String): String {
        return "Item updated"
    }

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: String): String {
        return "Item deleted"
    }

    @GetMapping("/filter")
    fun getItemsByFilter(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) bodyPart: String?,
    ): String {
        return "Items by filter"
    }

}