package com.froom.items.model.domain

import com.froom.user.model.domain.User
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class Item (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val uuid: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    var categoryType: CategoryType,

    @ElementCollection
    @CollectionTable(name = "item_color", joinColumns = [JoinColumn(name = "item_id")])
    @Column(name = "color")
    var color: List<Int>,

    @Lob
    var image: ByteArray,

    var imageFormat: String,

    @ManyToMany(mappedBy = "items", cascade = [CascadeType.ALL])
    val outfits: MutableList<Outfit> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY) // Changed to LAZY fetch type
    @JoinColumn(name = "user_id")
    val user: User
)
