package com.allade.afric.word.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.http.ResponseEntity
import java.util.*

@Entity
class User(
    var firstName: String = "",
    var lastName: String = "",
    @Column(nullable = false) var email: String = "",
    @JsonIgnore var password: String = "",
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    @CreationTimestamp var createdAt: Date = Date(),
    @UpdateTimestamp var updatedAt: Date = Date(),
    @OneToOne val role: Role? = null,
){
    private fun User?.toResponseEntity(): ResponseEntity<User> {
        return this.let { ResponseEntity.ok(it) ?: ResponseEntity.notFound().build() }
    }
}