package com.cubetiqs.demo.samplegraphql

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable
import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SpringBootApplication
class SampleGraphqlApplication

fun main(args: Array<String>) {
	runApplication<SampleGraphqlApplication>(*args)
}


@Entity
@Table(name = "employees")
data class Employee (
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
	var name: String? = null,
	@CreatedDate
	var createdDate: Date? = null
) : Serializable

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long>

@RestController
@RequestMapping("/employees")
class EmployeeRest @Autowired constructor(
	private val employeeRepository: EmployeeRepository
){
	private val mapper = ObjectMapper()

	@PostMapping
	fun create(
		@RequestParam(value = "json") body: String
	) : Employee? {
		val employee = mapper.readValue(body, Employee::class.java) ?: return null
		employee.id = null
		return employeeRepository.save(employee)
	}

	@GetMapping
	fun get(): List<Employee> {
		return employeeRepository.findAll()
	}
}