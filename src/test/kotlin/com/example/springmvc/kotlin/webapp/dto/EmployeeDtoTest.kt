package com.example.springmvc.kotlin.webapp.dto

import com.example.springmvc.kotlin.webapp.config.AppConfig
import com.example.springmvc.kotlin.webapp.domain.model.Employee
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.time.LocalDate

@SpringJUnitConfig(classes = [JacksonAutoConfiguration::class, AppConfig::class])
class EmployeeDtoTest {

    @Autowired
    lateinit var mapper: ObjectMapper

    private lateinit var target: EmployeeDto

    private lateinit var serialized: String

    @BeforeEach
    fun setup() {
        target = EmployeeDto(1, "高田", "純次", Employee.Sex.MALE,
            LocalDate.of(2021, 10, 29),
            "junji.takada@holywood.star.action.actor")

        serialized =
            "{" +
                "\"id\":1," +
                "\"first_name\":\"高田\"," +
                "\"last_name\":\"純次\"," +
                "\"sex\":\"MALE\"," +
                "\"birthday\":\"2021-10-29\"," +
                "\"mail_address\":\"junji.takada@holywood.star.action.actor\"" +
            "}"
    }

    @Test
    fun testSerialize() {
        // given
        val given = target

        // when
        val actual = mapper.writeValueAsString(given)

        // then
        val expected = serialized
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testDeserialize() {
        // given
        val given = serialized

        // when
        val actual = mapper.readValue(given, EmployeeDto::class.java)

        // then
        assertThat(actual).isEqualTo(target)
    }
}