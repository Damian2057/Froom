package com.froom.items.controller

import com.froom.authorization.util.toUser
import com.froom.items.model.command.UpdateItemCommand
import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.model.dto.ItemDto
import com.froom.items.service.ItemService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/item")
class ItemController(
    val itemService: ItemService
) {

    @GetMapping()
    fun getAllItems(authentication: Authentication): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(itemService.getAllItems(authentication.toUser()),
            HttpStatus.OK)
    }

    @GetMapping("/{uuid}")
    fun getItemByUuid(@PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.getItemByUuid(uuid, authentication.toUser()),
            HttpStatus.OK)
    }

    @PostMapping()
    fun createItem(@RequestParam("image") file: MultipartFile, authentication: Authentication): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.createItem(file, authentication.toUser()),
            HttpStatus.CREATED)
    }

    @PutMapping("/{uuid}")
    fun updateItem(@RequestBody @Valid command: UpdateItemCommand, @PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.updateItem(command, uuid, authentication.toUser()),
            HttpStatus.OK)
    }

    @PutMapping("/{uuid}/image")
    fun updateItemImage(@RequestParam("image") file: MultipartFile, @PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.updateItemImage(file, uuid, authentication.toUser()),
            HttpStatus.OK)
    }

    @DeleteMapping("/{uuid}")
    fun deleteItem(@PathVariable uuid: UUID, authentication: Authentication): ResponseEntity<HttpStatus> {
        itemService.deleteItem(uuid, authentication.toUser())
        return ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED)
    }

    @GetMapping("/filter")
    fun getItemsByFilter(
        @RequestParam(required = false) type: CategoryType?,
        @RequestParam(required = false) bodyPart: BodyPart?,
        authentication: Authentication
    ): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(itemService.getItemsByFilter(type, bodyPart, authentication.toUser()),
            HttpStatus.OK)
    }

}