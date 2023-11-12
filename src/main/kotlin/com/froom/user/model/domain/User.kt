package com.froom.user.model.domain

import com.froom.items.model.domain.Item
import com.froom.items.model.domain.Outfit
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="\"User\"")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    val name: String,

    val email: String,

    val password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val outfitUsers: MutableList<Outfit> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: MutableList<Item> = mutableListOf()
    )