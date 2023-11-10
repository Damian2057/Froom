package com.froom.items.controller

import com.froom.items.model.dto.OutfitDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/outfit")
class OutfitController {

    @PostMapping()
    fun createOutfit(): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @PutMapping("/{outfitId}/item/{itemId}")
    fun addItemToOutfit(
        @PathVariable outfitId: String,
        @PathVariable itemId: String
    ): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @DeleteMapping("/{outfitId}/item/{itemId}")
    fun deleteItemFromOutfit(
        @PathVariable outfitId: String,
        @PathVariable itemId: String
    ): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping()
    fun getAllOutfits(): ResponseEntity<List<OutfitDto>> {
        return ResponseEntity<List<OutfitDto>>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getOutfitById(@PathVariable id: String): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateOutfit(@PathVariable id: String): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteOutfit(@PathVariable id: String): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("/filter")
    fun getOutfitsByFilter(
        @RequestParam(required = false) name: String?,
    ): ResponseEntity<List<OutfitDto>> {
        return ResponseEntity<List<OutfitDto>>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @GetMapping("/random")
    fun getRandomOutfit(): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @PostMapping("/random")
    fun createRandomOutfit(): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            org.springframework.http.HttpStatus.OK)
    }
}