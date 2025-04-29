package com.coded.spring.pets

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name="PetsAPI")
@RestController
class PetsController(
    private val petsService: PetsService
) {
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "List of pets available in the store",
            content = [Content(mediaType = "application/json")]),
        ApiResponse(responseCode = "400", description = "An error occured while listing pets...",
            content = [Content(mediaType = "application/json")])
    )

    @GetMapping("/pets/v1/pets")
    fun listPets() : ResponseEntity<*> {
        return try{
            ResponseEntity.ok(
                ListPetsSuccessfulResponse(
                    pets = petsService.ListPets()
                )
            )
        } catch (e: IllegalStateException){
            ResponseEntity.badRequest().body(
                ListPetsFailureResponse(
                    error = "Sorry, pets are sleeping"
                )
            )
        }
    }
}

data class ListPetsSuccessfulResponse(
    val pets: List<Pet>
)

data class ListPetsFailureResponse(
    val error: String
)