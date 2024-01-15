package com.froom.items.service

import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.model.domain.Item
import com.froom.items.model.dto.ItemDto
import com.froom.items.repository.ItemRepository
import com.froom.items.util.toDto
import com.froom.user.model.domain.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ItemService(
    val itemRepository: ItemRepository
) {
    fun getAllItems(): List<ItemDto> {
        return emptyList()
    }

    fun getItemByUuid(uuid: String): ItemDto {
        return Any() as ItemDto
    }

    @Transactional
    fun createItem(file: MultipartFile, user: User): ItemDto {
        val item = Item(
            id = null,
            image = file.bytes,
            imageFormat = file.contentType!!,
            user = user,
            categoryType = CategoryType.T_SHIRT_TOP,
            color = listOf(1, 2, 3),
        )
        return itemRepository.save(item).toDto()
    }

    fun deleteItem(uuid: String): ItemDto {
        return Any() as ItemDto
    }

    fun getItemsByFilter(type: CategoryType?, bodyPart: BodyPart?): List<ItemDto> {
        return emptyList()
    }
}