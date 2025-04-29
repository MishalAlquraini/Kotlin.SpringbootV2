package com.coded.spring.test

import com.coded.spring.authentication.JWT.JwtService
import com.coded.spring.ordering.Item
import com.coded.spring.ordering.OrderRequest
import com.coded.spring.ordering.OrderResponse
import com.coded.spring.users.UserEntity
import com.coded.spring.users.UsersRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.MultiValueMap
import kotlin.test.assertEquals

// how to build test (not really working need to match the tests with the endpoints)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@Autowired
	lateinit var restTemplate: TestRestTemplate
// the correct way with jwt
	companion object {
		@JvmStatic
		@BeforeAll
		fun setUp(
			@Autowired usersRepository: UsersRepository,
			@Autowired passwordEncoder: PasswordEncoder,
		){
			usersRepository.deleteAll()
			val testUser = UserEntity(
				name = "coded",
				age = 24,
				username = "meshal",
				password = passwordEncoder.encode("meshal99775283")
			)
			val savedUser = usersRepository.save(testUser)
			print("savedUser ${savedUser.id}")
		}
	}

	@Test
	fun testWelcome(@Autowired jwtService: JwtService) {
		val token = jwtService.generateToken("meshal")
		val headers = HttpHeaders(
			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
		)
		val requestEntity = HttpEntity<String>(headers)

		val result = restTemplate.exchange(
			"/welcome",
			HttpMethod.GET,
			requestEntity,
			String::class.java
		)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals("Get in yoo!!!", result.body)
	}

	// the correct way with jwt
	@Test
	fun testCreateOrder(@Autowired jwtService: JwtService) {
		//Mock
		val token = jwtService.generateToken("meshal")
		val headers = HttpHeaders(
			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
		)
		val body = OrderRequest(
			userId = 1,
			items = listOf(Item("Chicken Burger", 3))
		)

		//Trigger
		val requestEntity = HttpEntity<OrderRequest>(body, headers)
		val actualResponse = restTemplate.exchange(
			"/orders", //Endpoint
			HttpMethod.POST,
			requestEntity,
			OrderResponse::class.java
		)

		//Assert
		assertEquals(HttpStatus.OK, actualResponse.statusCode)

		val expectedResponse = OrderResponse(
			userId = 1,
			items = listOf(
				Item("Chicken Burger", 3)
			)
		)
		assertEquals(expectedResponse, actualResponse.body, "Unexpected order created...")
	}

}


