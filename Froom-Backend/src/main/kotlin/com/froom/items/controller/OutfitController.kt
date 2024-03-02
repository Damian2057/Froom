package com.froom.items.controller

import com.froom.authorization.util.toUser
import com.froom.items.model.command.CreateOutfitCommand
import com.froom.items.model.command.UpdateOutfitCommand
import com.froom.items.model.dto.OutfitDto
import com.froom.items.service.OutfitService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/outfit")
class OutfitController(
    val outfitService: OutfitService
) {

    @PostMapping()
    fun createOutfit(@RequestBody @Valid command: CreateOutfitCommand, authentication: Authentication): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.createOutfit(command, authentication.toUser()),
            HttpStatus.CREATED)
    }

    @PutMapping("/{outfitUuid}/item/{itemUuid}")
    fun addItemToOutfit(
        @PathVariable outfitUuid: UUID,
        @PathVariable itemUuid: UUID,
        authentication: Authentication
    ): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.addItemToOutfit(outfitUuid, itemUuid, authentication.toUser()),
            HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{outfitUuid}/item/{itemUuid}")
    fun deleteItemFromOutfit(
        @PathVariable outfitUuid: UUID,
        @PathVariable itemUuid: UUID,
        authentication: Authentication
    ): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.deleteItemFromOutfit(outfitUuid, itemUuid, authentication.toUser()),
            HttpStatus.OK)
    }

    @GetMapping()
    fun getAllOutfits(authentication: Authentication): ResponseEntity<List<OutfitDto>> {
        return ResponseEntity<List<OutfitDto>>(outfitService.getAllOutfits(authentication.toUser()),
            HttpStatus.OK)
    }

    @GetMapping("/{uuid}")
    fun getOutfitByUuid(@PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.getOutfitByUuid(uuid, authentication.toUser()),
            HttpStatus.OK)
    }

    @PutMapping("/{uuid}")
    fun updateOutfit(@RequestBody @Valid command: UpdateOutfitCommand, @PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.updateOutfit(command, uuid, authentication.toUser()),
            HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{uuid}")
    fun deleteOutfit(@PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<HttpStatus> {
        outfitService.deleteOutfit(uuid, authentication.toUser())
        return ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED)
    }

    @GetMapping("/filter")
    fun getOutfitsByFilter(
        @RequestParam(required = true) name: String,
        authentication: Authentication
    ): ResponseEntity<List<OutfitDto>> {
        return ResponseEntity<List<OutfitDto>>(outfitService.getOutfitsByFilter(name, authentication.toUser()),
            HttpStatus.OK)
    }

    @GetMapping("/random")
    fun getRandomOutfit(authentication: Authentication): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.getRandomOutfit(authentication.toUser()),
            HttpStatus.OK)
    }

    @PostMapping("/random")
    fun createRandomOutfit(authentication: Authentication): ResponseEntity<OutfitDto> {
        return ResponseEntity<OutfitDto>(outfitService.createRandomOutfit(authentication.toUser()),
            HttpStatus.CREATED)
    }
}