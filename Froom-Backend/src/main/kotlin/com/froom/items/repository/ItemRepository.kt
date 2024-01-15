package com.froom.items.repository

import com.froom.items.model.domain.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ItemRepository: JpaRepository<Item, Int> {

    fun findByUuid(uuid: UUID): Item?
}