package com.example.springmvc.kotlin.webapp.domain.repository

import com.example.springmvc.kotlin.webapp.domain.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long>
