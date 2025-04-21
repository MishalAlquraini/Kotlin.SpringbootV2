package com.coded.spring.test

import com.coded.spring.ordering.OrderRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import javax.swing.Spring
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun testOrderList() {
		val result = restTemplate.getForEntity(
			"/welcome",
			String::class.java
		)
		val expected = "Get in yoo"

		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(expected, result.body)
	}

	@Test
	fun testCreateOrder(){
		val result = restTemplate.postForEntity(
			"/orders", //Endpoint
			OrderRequest(1, listOf()), //Request body
			String::class.java // Response body
		)
		assertEquals(HttpStatus.OK, result.statusCode)
	}

}
