package com.example.springmvc.kotlin.webapp.domain.repository

import com.example.springmvc.kotlin.webapp.domain.model.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository: JpaRepository<Department, Long>
