package com.froom.items.repository

import com.froom.items.model.domain.Outfit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OutfitRepository: JpaRepository<Outfit, Int> {

    fun findOutfitByUserUuid(userUuid: UUID): List<Outfit>

    fun findOutfitByUuid(uuid: UUID): Outfit?

    fun findOutfitByName(name: String): Outfit?
}