package com.froom.items.service

import com.froom.exception.type.ItemCreationException
import com.froom.exception.type.ItemNotFoundException
import com.froom.items.model.command.UpdateItemCommand
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
import kotlinx.coroutines.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val categoryAdapter: CategoryAdapter,
    private val colorAdapter: ColorAdapter,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun getAllItems(user: User): List<ItemDto> {
        logger.info("User items retrieved: ${user.userName}")
        itemRepository.findItemByUserUuid(user.uuid).let {
            return it.map { item -> item.toDto() }
        }
    }

    @Transactional
    fun getItemByUuid(uuid: UUID, user: User): ItemDto {
        logger.info("Item retrieved: $uuid")
        val item = itemRepository.findByUuid(uuid) ?: throw ItemNotFoundException("Item not found")
        if (item.user.uuid != user.uuid) {
            throw ItemNotFoundException("Cannot retrieve item: $uuid, user: ${user.uuid} does not own the item.")
        }
        return item.toDto()
    }

    @Transactional
    fun createItem(file: MultipartFile, user: User): ItemDto {
        return try {
            val categoryDeferred: Deferred<CategoryType> = CoroutineScope(Dispatchers.Default).async { getCategory(file) }
            val colorDeferred: Deferred<List<Int>> = CoroutineScope(Dispatchers.Default).async { getColor(file) }

            val (category, color) = runBlocking {
                awaitAll(categoryDeferred, colorDeferred)
            }

            val item = Item(
                id = null,
                image = file.bytes,
                imageFormat = file.contentType!!,
                user = user,
                categoryType = category as CategoryType,
                color = color as List<Int>
            )

            logger.info("Item created: $item")

            itemRepository.save(item).toDto()
        } catch (e: Exception) {
            throw ItemCreationException("Item creation failed", e)
        }
    }

    @Transactional
    fun deleteItem(uuid: UUID, user: User) {
        val item = itemRepository.findByUuid(uuid) ?: throw ItemNotFoundException("Item not found")
        if (item.user.uuid != user.uuid) {
            throw ItemNotFoundException("Cannot delete item: $uuid, user: ${user.uuid} does not own the item.")
        }
        itemRepository.delete(item)
        logger.info("Item deleted: $uuid")
    }

    @Transactional
    fun getItemsByFilter(type: CategoryType?, bodyPart: BodyPart?, user: User): List<ItemDto> {
        return if (type != null) {
            itemRepository.findItemByCategoryTypeAndUserUuid(type, user.uuid).map { it.toDto() }
        } else {
            itemRepository.findItemByUserUuid(user.uuid).map { it.toDto() }
        }
    }

    private fun getCategory(file: MultipartFile): CategoryType {
        return categoryAdapter.getCategory(file)
    }

    private fun getColor(file: MultipartFile): List<Int> {
        return colorAdapter.getColor(file)
    }

    @Transactional
    fun updateItem(command: UpdateItemCommand, uuid: UUID, toUser: User): ItemDto {
        val item = itemRepository.findByUuid(uuid) ?: throw ItemNotFoundException("Item not found")
        if (item.user.uuid != toUser.uuid) {
            throw ItemNotFoundException("Cannot update item: $uuid, user: ${toUser.uuid} does not own the item.")
        }
        item.categoryType = command.category
        item.color = command.color
        logger.info("Item updated: $item")
        return itemRepository.save(item).toDto()

    }

    @Transactional
    fun updateItemImage(file: MultipartFile, uuid: UUID, toUser: User): ItemDto {
        val item = itemRepository.findByUuid(uuid) ?: throw ItemNotFoundException("Item not found")
        if (item.user.uuid != toUser.uuid) {
            throw ItemNotFoundException("Cannot update item: $uuid, user: ${toUser.uuid} does not own the item.")
        }
        item.image = file.bytes
        item.imageFormat = file.contentType!!
        return itemRepository.save(item).toDto()
    }

}