package com.example.springmvc.kotlin.webapp.domain.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "authentications")
data class Authentication(
    @Id
    val id: Long?,

    @Column(nullable = false)
    val password: String?,

    @Column(nullable = false)
    val validDate: LocalDate?,

    @OneToOne
    @MapsId
    val employee: Employee?

): Serializable {
    constructor(password: String, validDate: LocalDate): this(null, password, validDate, null)
    fun withId(id: Long) = Authentication(id, password, validDate, employee)
    fun withEmployee(employee: Employee) = Authentication(id, password, validDate, employee)
}