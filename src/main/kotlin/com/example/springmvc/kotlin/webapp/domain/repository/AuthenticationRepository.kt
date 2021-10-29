package com.example.springmvc.kotlin.webapp.domain.repository

import com.example.springmvc.kotlin.webapp.domain.model.Authentication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthenticationRepository: JpaRepository<Authentication, Long>
