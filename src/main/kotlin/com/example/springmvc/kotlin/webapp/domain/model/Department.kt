package com.example.springmvc.kotlin.webapp.domain.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "departments",
    uniqueConstraints = [UniqueConstraint(name = "uk_departments_name", columnNames = ["name"])])
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val name: String?,

    @Column(nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime?,

    @Column(nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime?,

    val deletedAt: LocalDateTime?,

    @OneToMany(mappedBy = "department")
    @PrimaryKeyJoinColumn
    val employees: List<Employee>?

) : Serializable {
    constructor(name: String): this(null, name, null, null, null, null)

    fun withId(id: Long) = Department(id, name, createdAt, updatedAt, deletedAt, employees)

    companion object {
        fun ofId(id: Long) = Department(id, null, null, null, null, null)
    }

}