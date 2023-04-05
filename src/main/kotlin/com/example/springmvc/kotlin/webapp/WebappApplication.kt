package com.example.springmvc.kotlin.webapp

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebappApplication {}

fun main(args: Array<String>) {
    runApplication<WebappApplication>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}
