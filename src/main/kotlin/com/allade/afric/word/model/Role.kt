package com.allade.afric.word.model

import jakarta.persistence.*

@Entity
class Role(
    @Column var name: String="",
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long?=null,
    @OneToMany val user: Set<User> = setOf(),
)