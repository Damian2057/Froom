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
@RequestMapping("/outfit")
class OutfitController {

    @PostMapping()
    fun createOutfit(): String {
        return "Outfit created"
    }

    @PutMapping("/{outfitId}/item/{itemId}")
    fun addItemToOutfit(
        @PathVariable outfitId: String,
        @PathVariable itemId: String
    ): String {
        return "Item added to outfit"
    }

    @DeleteMapping("/{outfitId}/item/{itemId}")
    fun deleteItemFromOutfit(
        @PathVariable outfitId: String,
        @PathVariable itemId: String
    ): String {
        return "Item deleted from outfit"
    }

    @GetMapping()
    fun getAllOutfits(): String {
        return "All outfits"
    }

    @GetMapping("/{id}")
    fun getOutfitById(@PathVariable id: String): String {
        return "Outfit by id"
    }

    @PutMapping("/{id}")
    fun updateOutfit(@PathVariable id: String): String {
        return "Outfit updated"
    }

    @DeleteMapping("/{id}")
    fun deleteOutfit(@PathVariable id: String): String {
        return "Outfit deleted"
    }

    @GetMapping("/filter")
    fun getOutfitsByFilter(
        @RequestParam(required = false) name: String?,
    ): String {
        return "Outfits by filter"
    }
}