package com.allade.afric.word.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Role(
    @Column var name: String="",
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long?=null,
    @ManyToOne val user: User = User(),
)