package com.example.springmvc.kotlin.webapp.dto

import com.example.springmvc.kotlin.webapp.domain.model.Employee
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class EmployeeDto(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val sex: Employee.Sex?,
    val birthday: LocalDate?,
    val mailAddress: String?
) {
    constructor(employee: Employee): this(
        employee.id, employee.firstName, employee.lastName,
        employee.sex, employee.birthday, employee.mailAddress
    )
}