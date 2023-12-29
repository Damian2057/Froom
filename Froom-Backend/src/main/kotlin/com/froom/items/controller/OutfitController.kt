package com.froom.items.controller

import com.froom.items.model.dto.OutfitDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/outfit")
class OutfitController {

    @PostMapping()
    fun createOutfit(): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.CREATED)
    }

    @PutMapping("/{outfitUuid}/item/{itemUuid}")
    fun addItemToOutfit(
        @PathVariable outfitUuid: String,
        @PathVariable itemUuid: String
    ): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{outfitUuid}/item/{itemUuid}")
    fun deleteItemFromOutfit(
        @PathVariable outfitUuid: String,
        @PathVariable itemUuid: String
    ): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.OK)
    }

    @GetMapping()
    fun getAllOutfits(): ResponseEntity<List<OutfitDto>> {
        return ResponseEntity<List<OutfitDto>>(null,
            HttpStatus.OK)
    }

    @GetMapping("/{uuid}")
    fun getOutfitByUuid(@PathVariable uuid: String): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.OK)
    }

    @PutMapping("/{uuid}")
    fun updateOutfit(@PathVariable uuid: String): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{uuid}")
    fun deleteOutfit(@PathVariable uuid: String): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.OK)
    }

    @GetMapping("/filter")
    fun getOutfitsByFilter(
        @RequestParam(required = false) name: String?,
    ): ResponseEntity<List<OutfitDto>> {
        return ResponseEntity<List<OutfitDto>>(null,
            HttpStatus.OK)
    }

    @GetMapping("/random")
    fun getRandomOutfit(): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.OK)
    }

    @PostMapping("/random")
    fun createRandomOutfit(): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(null,
            HttpStatus.CREATED)
    }
}