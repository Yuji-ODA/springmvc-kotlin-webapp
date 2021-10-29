package com.example.springmvc.kotlin.webapp.domain.repository

import com.example.springmvc.kotlin.webapp.config.DataBaseLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(DataBaseLoader::class)
class AuthenticationRepositoryTest {

    @Autowired
    lateinit var target: AuthenticationRepository

    @Test
    fun testFindAll() {
        // when
        val actual = target.findAll()

        // then
        assertThat(actual).hasSize(4)
    }
}