package com.froom.items.model.domain

import com.froom.user.model.domain.User
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class Item (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val uuid: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    val categoryType: CategoryType,

    @Enumerated(EnumType.STRING)
    val color: Color,

    @Lob
    val image: ByteArray,

    @ManyToMany(mappedBy = "items")
    val outfits: MutableList<Outfit> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)