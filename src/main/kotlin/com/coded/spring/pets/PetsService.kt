package com.coded.spring.pets

import jakarta.inject.Named
import com.hazelcast.logging.Logger



@Named
class PetsService(
    private val petsClient: PetsClient
){
    fun ListPets() : List<Pet> = petsClient.getPets().map {
        Pet(
            name = it.name,
            type = it.type
        )
    }
}



data class Pet(
    val name: String,
    val type: String
)
//val petsCache = serverCache