package com.example.springmvc.kotlin.webapp.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @GetMapping("/")
    fun root(auth: Authentication?) = "おはようございます、${auth?.name}さま。"
}