package com.allade.afric.word.model

import jakarta.persistence.*
import java.util.UUID

@Table(name = "roles")
@Entity
class Role(
    @Column var name: String = "",
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: UUID? = null,
)
