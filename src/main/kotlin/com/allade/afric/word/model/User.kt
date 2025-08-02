package com.allade.afric.word.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.PostPersist
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.http.ResponseEntity
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    var firstName: String = "",
    var lastName: String = "",
    @Column(nullable = false) var email: String = "",
    @JsonIgnore var password: String = "",
    var phoneNumber: String = "",
    @ManyToOne val role: Role? = null,
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
) {
    @CreationTimestamp
    var createdAt: Date = Date()

    @UpdateTimestamp
    var updatedAt: Date = Date()
}
