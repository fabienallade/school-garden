package com.allade.afric.word.data.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Int? = null

    private var username: String? = null

    private var email: String? = null

    private var password: String? = null

    private var firstName: String? = null

    private var lastName: String? = null

}