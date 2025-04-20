package com.coded.spring.Profiles

import com.coded.spring.users.UsersRepository
import jakarta.inject.Named
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder

@Named
class ProfilesService (
    private val profilesRepository: ProfilesRepository,
    private val usersRepository: UsersRepository
){
    fun createProfile(request: ProfileRequest): ProfileResponse{
        val userId = usersRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)?.id ?:
        throw IllegalArgumentException()
        val newProfile = ProfileEntity(
            userID = userId,
            firstName = request.firstName,
            lastName = request.lastName,
            phoneNumber = request.phoneNumber
        )
        val savedProfile = profilesRepository.save(newProfile)
        return ProfileResponse(savedProfile.firstName,savedProfile.lastName,savedProfile.phoneNumber)
    }
}

data class ProfileRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: Long
)

data class ProfileResponse(
    val firstName: String,
    val lastName: String,
    val phoneNumber: Long

)

