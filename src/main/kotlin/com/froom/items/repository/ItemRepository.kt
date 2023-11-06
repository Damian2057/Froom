package com.froom.items.repository

import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<Dress, Int> {
}