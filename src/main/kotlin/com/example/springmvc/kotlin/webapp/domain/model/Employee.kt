package com.example.springmvc.kotlin.webapp.domain.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val firstName: String?,

    @Column(nullable = false)
    val lastName: String?,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val sex: Sex?,

    @Column(nullable = false)
    val birthday: LocalDate?,

    @Column(nullable = false)
    val mailAddress: String?,

    @Column(nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime?,

    @Column(nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime?,

    val deletedAt: LocalDateTime?,

    @ManyToOne(optional = false)
    @JoinColumn(name = "departmentId")
    val department: Department?,

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    val authentication: Authentication?

): Serializable {

    constructor(firstName: String, lastName: String, sex: Sex, birthday: LocalDate, mailAddress: String):
            this(null, firstName, lastName, sex, birthday, mailAddress, null, null, null, null, null)

    fun withId(id: Long) =
        Employee(id, firstName, lastName, sex, birthday, mailAddress, createdAt, updatedAt, deletedAt, department, authentication)

    fun withDepartment(department: Department) =
        Employee(id, firstName, lastName, sex, birthday, mailAddress, createdAt, updatedAt, deletedAt, department, authentication)

    companion object {
        fun ofId(id: Long) = Employee(id, null, null, null, null, null, null, null, null, null, null)
    }

    enum class Sex {
        MALE, FEMALE
    }
}

