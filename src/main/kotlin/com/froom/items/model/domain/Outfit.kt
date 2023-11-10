package com.froom.items.model.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class Outfit (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val uuid: UUID = UUID.randomUUID(),

    val name: String,
)