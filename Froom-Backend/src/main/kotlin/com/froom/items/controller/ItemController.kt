package com.froom.items.controller

import com.froom.authorization.util.toUser
import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.model.dto.ItemDto
import com.froom.items.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/item")
class ItemController(
    val itemService: ItemService
) {

    @GetMapping()
    fun getAllItems(): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(itemService.getAllItems(),
            HttpStatus.OK)
    }

    @GetMapping("/{uuid}")
    fun getItemByUuid(@PathVariable uuid: UUID): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.getItemByUuid(uuid),
            HttpStatus.OK)
    }

    @PostMapping()
    fun createItem(@RequestParam("image") file: MultipartFile, authentication: Authentication): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.createItem(file, authentication.toUser()),
            HttpStatus.CREATED)
    }

    @PutMapping("/{uuid}")
    fun updateItem(@PathVariable uuid: String): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(null,
            HttpStatus.OK)
    }

    @DeleteMapping("/{uuid}")
    fun deleteItem(@PathVariable uuid: String): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.deleteItem(uuid),
            HttpStatus.OK)
    }

    @GetMapping("/filter")
    fun getItemsByFilter(
        @RequestParam(required = false) type: CategoryType?,
        @RequestParam(required = false) bodyPart: BodyPart?,
    ): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(itemService.getItemsByFilter(type, bodyPart),
            HttpStatus.OK)
    }

}