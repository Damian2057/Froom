package com.froom.items.model.domain

import com.froom.user.model.domain.User
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class Outfit (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val uuid: UUID = UUID.randomUUID(),

    var name: String,

    @ManyToOne(fetch = FetchType.LAZY) // Changed to LAZY fetch type
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToMany
    @JoinTable(
        name = "outfit_item",
        joinColumns = [JoinColumn(name = "outfit_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: MutableList<Item> = mutableListOf()
)