package com.froom.user.model.domain

import com.froom.items.model.domain.Item
import com.froom.items.model.domain.Outfit
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import java.util.*

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="\"User\"")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    val uuid: UUID = UUID.randomUUID(),

    var userName: String,

    @Column(unique = true)
    var email: String,

    var password: String,

    var birthDate: Date,

    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val outfitUsers: MutableList<Outfit> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: MutableList<Item> = mutableListOf()
)