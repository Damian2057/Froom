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
    val id: Long?,

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val uuid: UUID = UUID.randomUUID(),

    val userName: String,

    val email: String,

    val password: String,

    val birthDate: Date,

    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val outfitUsers: MutableList<Outfit> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: MutableList<Item> = mutableListOf()
    )