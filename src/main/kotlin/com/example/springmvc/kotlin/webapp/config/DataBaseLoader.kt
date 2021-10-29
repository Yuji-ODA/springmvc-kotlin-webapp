package com.example.springmvc.kotlin.webapp.config

import com.example.springmvc.kotlin.webapp.domain.model.Authentication
import com.example.springmvc.kotlin.webapp.domain.model.Department
import com.example.springmvc.kotlin.webapp.domain.model.Employee
import com.example.springmvc.kotlin.webapp.domain.repository.AuthenticationRepository
import com.example.springmvc.kotlin.webapp.domain.repository.DepartmentRepository
import com.example.springmvc.kotlin.webapp.domain.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import javax.annotation.PostConstruct

@Component
@Profile(value = ["default", "local", "test"])
class DataBaseLoader(
    @Value("\${spring.jpa.hibernate.ddl-auto}")
    private val ddlAuto: String,
    private val departmentRepository: DepartmentRepository,
    private val employeeRepository: EmployeeRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    @PostConstruct
    @Transactional
    fun loadData() {
        if (listOf("update", "create", "create-drop").none { it == ddlAuto }) {
            return
        }

        val department1 = Department("汲み取り係").withId(1)
        val department2 = Department("いきものがかり").withId(2)
        val department3 = Department("行き係").withId(3)

        val employee1 = Employee("坂本", "冬美", Employee.Sex.FEMALE, LocalDate.of(1967, 3, 30), "winter.slope@fc5.so-net.ne.jp")
            .withDepartment(Department.ofId(2))
            .withId(1)
        val employee2 = Employee("坂本", "勇人", Employee.Sex.MALE, LocalDate.of(1988, 12, 14), "hsakamo@fc5.so-net.ne.jp")
            .withDepartment(Department.ofId(1))
            .withId(2)
        val employee3 = Employee("坂本", "龍一", Employee.Sex.MALE, LocalDate.of(1952, 1, 17), "ryu.sakamoto@fc5.so-net.ne.jp")
            .withDepartment(Department.ofId(1))
            .withId(3)
        val employee4 = Employee("坂本", "ちゃん", Employee.Sex.FEMALE, LocalDate.of(1966, 4, 2), "chan@fc5.so-net.ne.jp")
            .withDepartment(Department.ofId(3))
            .withId(4)

        val authentication1 = Authentication("冬美のパスワードは丸見え", LocalDate.of(2067, 3, 30))
            .withEmployee(Employee.ofId(1))
            .withId(1)
        val authentication2 = Authentication("勇人のパスワードは丸見え", LocalDate.of(2088, 12, 14))
            .withEmployee(Employee.ofId(2))
            .withId(2)
        val authentication3 = Authentication("龍一は雲隠れ", LocalDate.of(2052, 1, 17))
            .withEmployee(Employee.ofId(3))
            .withId(3)
        val authentication4 = Authentication("坂本ちゃんはどこへいったのか？", LocalDate.of(2066, 4, 2))
            .withEmployee(Employee.ofId(4))
            .withId(4)

        val departments = listOf(department1, department2, department3)
        departmentRepository.saveAll(departments)

        val employees = listOf(employee1, employee2, employee3, employee4)
        employeeRepository.saveAll(employees)

        val authentications = listOf(authentication1, authentication2, authentication3, authentication4)
        authenticationRepository.saveAll(authentications)
    }
}
