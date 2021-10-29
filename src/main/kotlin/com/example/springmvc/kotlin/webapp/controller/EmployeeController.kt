package com.example.springmvc.kotlin.webapp.controller

import com.example.springmvc.kotlin.webapp.domain.repository.EmployeeRepository
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["employee"])
class EmployeeController(
    private val employeeRepository: EmployeeRepository
) {

    @GetMapping
    @Transactional(readOnly = true)
    fun showEmployees(authentication: Authentication, model: Model): String {
        model.addAttribute("employees", employeeRepository.findAll())
        return "employees"
    }
}