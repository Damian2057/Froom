package com.froom.items.repository

import com.froom.items.model.domain.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Int> {
}