package com.coded.spring.pets

import jakarta.inject.Named
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Named
class PetsClient {
    fun getPets(): List<PetDTO>{
        val restTemplate = RestTemplate()
        val url = "https://pets-react-query-backend.eapi.joincoded.com/pets"

        val response = restTemplate.exchange<List<PetDTO>>(
            url = url,
            method = HttpMethod.GET,
            requestEntity = null,
            object : ParameterizedTypeReference<List<PetDTO?>?>() {
            }
        )
        return response.body ?: listOf()
    }
}

data class PetDTO(
    val id: Long,
    val name: String,
    val type: String,
    val adopted: String,
    val image: String
)