package com.example.springmvc.kotlin.webapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity(debug = true)
class WebSecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http.csrf().disable()
            .authorizeHttpRequests {
                it.requestMatchers("/actuator/**", "/console/**").permitAll()
                    .anyRequest().authenticated()
            }
            .headers {
                it.frameOptions().sameOrigin()
            }
            .formLogin(withDefaults())
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            }
            .build()

    @Bean
    fun users(): UserDetailsService =
        User.withDefaultPasswordEncoder().run {
            val user = username("user")
                .password("pass")
                .roles("USER")
                .build()
            val admin = username("admin")
                .password("pass")
                .roles("USER", "ADMIN")
                .build()
            InMemoryUserDetailsManager(user, admin)
        }
}
