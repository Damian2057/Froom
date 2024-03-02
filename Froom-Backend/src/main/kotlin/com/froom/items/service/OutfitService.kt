package com.froom.items.service

import com.froom.exception.type.IncorrectOutfitException
import com.froom.exception.type.OutfitNotFoundException
import com.froom.items.model.command.CreateOutfitCommand
import com.froom.items.model.command.UpdateOutfitCommand
import com.froom.items.model.domain.Item
import com.froom.items.model.domain.Outfit
import com.froom.items.model.dto.OutfitDto
import com.froom.items.repository.ItemRepository
import com.froom.items.repository.OutfitRepository
import com.froom.items.util.toDto
import com.froom.user.model.domain.User
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.sqrt

@Service
class OutfitService(
    private val itemRepository: ItemRepository,
    private val outfitRepository: OutfitRepository
) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun createOutfit(command: CreateOutfitCommand, toUser: User): OutfitDto {
        val items = command.itemUuids.mapNotNull { itemRepository.findByUuid(it) }
        val outfit = Outfit(
            id = null,
            name = command.name,
            user = toUser,
            items = items.toMutableList()
        )
        checkIfItemsInOutfitAreCorrect(items)
        logger.info("Outfit created: ${outfit.uuid}")
        return outfitRepository.save(outfit).toDto()
    }

    @Transactional
    fun addItemToOutfit(outfitUuid: UUID, itemUuid: UUID, toUser: User): OutfitDto {
        val outfit = outfitRepository.findOutfitByUuid(outfitUuid) ?: throw OutfitNotFoundException("Outfit not found")
        if (outfit.user.uuid != toUser.uuid) {
            throw OutfitNotFoundException("Cannot add item to outfit: $outfitUuid, user: ${toUser.uuid} does not own the outfit.")
        }
        if (outfit.items.size > 3) {
            throw IncorrectOutfitException("Cannot add more than 5 items to outfit")
        }
        val item = itemRepository.findByUuid(itemUuid) ?: throw OutfitNotFoundException("Item not found")
        outfit.items.add(item)
        logger.info("Item added to outfit: $outfitUuid")
        return outfitRepository.save(outfit).toDto()
    }

    @Transactional
    fun deleteItemFromOutfit(outfitUuid: UUID, itemUuid: UUID, toUser: User): OutfitDto {
        val outfit = outfitRepository.findOutfitByUuid(outfitUuid) ?: throw OutfitNotFoundException("Outfit not found")
        if (outfit.user.uuid != toUser.uuid) {
            throw OutfitNotFoundException("Cannot delete item from outfit: $outfitUuid, user: ${toUser.uuid} does not own the outfit.")
        }
        val item = itemRepository.findByUuid(itemUuid) ?: throw OutfitNotFoundException("Item not found")
        outfit.items.remove(item)
        logger.info("Item deleted from outfit: $outfitUuid")
        return outfitRepository.save(outfit).toDto()
    }

    private fun checkIfItemsInOutfitAreCorrect(items: List<Item>) {
        if (items.isEmpty()) {
            throw IncorrectOutfitException("Cannot create outfit with no items")
        }
        val bodyParts = items.map { it.categoryType.bodyPart }
        if (bodyParts.size != bodyParts.distinct().size) {
            throw IncorrectOutfitException("Cannot create outfit with duplicate body parts")
        }
    }

    @Transactional
    fun getAllOutfits(toUser: User): List<OutfitDto> {
        return outfitRepository.findOutfitByUserUuid(toUser.uuid).map { it.toDto() }
    }

    @Transactional
    fun getOutfitByUuid(uuid: UUID, toUser: User): OutfitDto {
        val outfit = outfitRepository.findOutfitByUuid(uuid) ?: throw OutfitNotFoundException("Outfit not found")
        if (outfit.user.uuid != toUser.uuid) {
            throw OutfitNotFoundException("Cannot retrieve outfit: $uuid, user: ${toUser.uuid} does not own the outfit.")
        }
        return outfit.toDto()
    }

    @Transactional
    fun deleteOutfit(uuid: UUID, toUser: User) {
        val outfit = outfitRepository.findOutfitByUuid(uuid) ?: throw OutfitNotFoundException("Outfit not found")
        if (outfit.user.uuid != toUser.uuid) {
            throw OutfitNotFoundException("Cannot delete outfit: $uuid, user: ${toUser.uuid} does not own the outfit.")
        }
        outfitRepository.delete(outfit)
    }

    @Transactional
    fun createRandomOutfit(toUser: User): OutfitDto {
        val items = itemRepository.findItemByUserUuid(toUser.uuid)
        val baseItem = items.random()
        if (items.size < 10) {
            throw IncorrectOutfitException("Cannot create random outfit with less than 10 items")
        }
        val similarItems = getSimilarItemsToBaseItemByColor(items, baseItem)
        val outfit = Outfit(
            id = null,
            name = "Random outfit",
            user = toUser,
            items = similarItems.toMutableList()
        )
        logger.info("Random outfit created: ${outfit.uuid}")
        return outfitRepository.save(outfit).toDto()
    }

    @Transactional
    fun getRandomOutfit(toUser: User): OutfitDto {
        val outfits = outfitRepository.findOutfitByUserUuid(toUser.uuid)
        if (outfits.isEmpty()) {
            throw OutfitNotFoundException("No outfits found for user: ${toUser.uuid}")
        }
        return outfits.random().toDto()
    }

    @Transactional
    fun updateOutfit(command: UpdateOutfitCommand, uuid: UUID, toUser: User): OutfitDto {
        val outfit = outfitRepository.findOutfitByUuid(uuid) ?: throw OutfitNotFoundException("Outfit not found")
        if (outfit.user.uuid != toUser.uuid) {
            throw OutfitNotFoundException("Cannot update outfit: $uuid, user: ${toUser.uuid} does not own the outfit.")
        }
        outfit.name = command.name
        logger.info("Outfit updated: $uuid")
        return outfitRepository.save(outfit).toDto()
    }

    fun getSimilarItemsToBaseItemByColor(items: List<Item>, baseItem: Item): List<Item> {
        var itemByBodyTypeMap = items.groupBy { it.categoryType.bodyPart }
        val similarItems = mutableListOf<Item>()
        similarItems.add(baseItem)
        itemByBodyTypeMap = itemByBodyTypeMap.filterKeys { it != baseItem.categoryType.bodyPart }

        itemByBodyTypeMap.forEach { (_, items) ->
            var similarItem = items[0]
            items.forEach { item ->
                if (calculateDistanceBetweenRGBColors(baseItem.color, item.color) < calculateDistanceBetweenRGBColors(baseItem.color, similarItem.color)) {
                    similarItem = item
                }
            }
            similarItems.add(similarItem)
        }

        return similarItems
    }

    fun calculateDistanceBetweenRGBColors(color1: List<Int>, color2: List<Int>): Double {
        val redDifference = color1[0] - color2[0]
        val greenDifference = color1[1] - color2[1]
        val blueDifference = color1[2] - color2[2]
        return sqrt((redDifference * redDifference + greenDifference * greenDifference + blueDifference * blueDifference).toDouble())
    }

    fun getOutfitsByFilter(name: String, toUser: User): List<OutfitDto> {
        val outfit = outfitRepository.findOutfitByName(name) ?: throw OutfitNotFoundException("Outfit not found")
        if (outfit.user.uuid != toUser.uuid) {
            throw OutfitNotFoundException("Cannot retrieve outfit: $name, user: ${toUser.uuid} does not own the outfit.")
        }
        return outfitRepository.findOutfitByUserUuid(toUser.uuid).map { it.toDto() }
    }
}