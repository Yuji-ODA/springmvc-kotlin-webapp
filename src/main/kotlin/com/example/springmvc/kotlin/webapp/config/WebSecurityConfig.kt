package com.example.springmvc.kotlin.webapp.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@EnableWebSecurity(debug = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/actuator/**", "/console/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .formLogin()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    }

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
