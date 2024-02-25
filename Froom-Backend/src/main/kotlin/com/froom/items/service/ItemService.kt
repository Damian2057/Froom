package com.froom.items.service

import com.froom.exception.type.ItemCreationException
import com.froom.exception.type.ItemNotFoundException
import com.froom.items.model.domain.BodyPart
import com.froom.items.model.domain.CategoryType
import com.froom.items.model.domain.Item
import com.froom.items.model.dto.ItemDto
import com.froom.items.repository.ItemRepository
import com.froom.items.util.toDto
import com.froom.user.model.domain.User
import com.froom.util.retrofit.category.CategoryAdapter
import com.froom.util.retrofit.color.ColorAdapter
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ItemService(
    val itemRepository: ItemRepository,
    val categoryAdapter: CategoryAdapter,
    val colorAdapter: ColorAdapter
) {
    fun getAllItems(): List<ItemDto> {
        return emptyList()
    }

    fun getItemByUuid(uuid: UUID): ItemDto {
        return itemRepository.findByUuid(uuid)?.toDto() ?: throw ItemNotFoundException("Item not found")
    }

    @Transactional
    fun createItem(file: MultipartFile, user: User): ItemDto {
        println(categoryAdapter.getCategory(file))
        println(colorAdapter.getColor(file))
//        try {
//            val item = Item(
//                id = null,
//                image = file.bytes,
//                imageFormat = file.contentType!!,
//                user = user,
//                categoryType = getCategory(file),
//                color = getColor(file)
//            )
//            return itemRepository.save(item).toDto()
//        } catch (e: Exception) {
//            throw ItemCreationException("Item creation failed", e)
//        }
        return Any() as ItemDto
    }

    fun deleteItem(uuid: String): ItemDto {
        return Any() as ItemDto
    }

    fun getItemsByFilter(type: CategoryType?, bodyPart: BodyPart?): List<ItemDto> {
        return emptyList()
    }

    private fun getCategory(file: MultipartFile): CategoryType {
        return CategoryType.T_SHIRT_TOP
    }

    private fun getColor(file: MultipartFile): List<Int> {
        return listOf(1, 2, 3)
    }
}