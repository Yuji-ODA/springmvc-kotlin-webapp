package com.example.springmvc.kotlin.webapp.domain.repository

import com.example.springmvc.kotlin.webapp.config.DataBaseLoader
import com.example.springmvc.kotlin.webapp.domain.model.Department
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@Import(DataBaseLoader::class)
class DepartmentRepositoryTest {

    @Autowired
    lateinit var target: DepartmentRepository

    @Test
    fun testFindAll() {

        // when
        val actual = target.findAll()

        // then
        assertThat(actual)
            .hasSize(3)
            .extracting(Department::id, Department::name)
            .containsExactlyInAnyOrder(tuple(1L, "汲み取り係"), tuple(2L, "いきものがかり"), tuple(3L, "行き係"))
    }

    @Test
    fun testFindByIdOrNull() {
        // when
        val actual = target.findByIdOrNull(2)

        // then
        assertThat(actual)
            .hasFieldOrPropertyWithValue("id", 2L)
            .hasFieldOrPropertyWithValue("name", "いきものがかり")
    }

    @Test
    fun testSave() {
        // given
        target.save(Department("干され部屋"))

        // when
        val actual = target.findAll()

        // then
        assertThat(actual)
            .hasSize(4)
            .flatExtracting(Department::name)
            .containsExactlyInAnyOrder("汲み取り係", "いきものがかり", "行き係", "干され部屋")
    }
}