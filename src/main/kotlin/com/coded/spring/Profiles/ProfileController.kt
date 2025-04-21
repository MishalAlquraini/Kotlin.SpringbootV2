package com.coded.spring.Profiles

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(
    val profilesRepository: ProfilesRepository,
    val profilesService: ProfilesService
){
    @PostMapping("/auth/users/v1/profiles")
        fun createProfiles(@RequestBody request: ProfileRequest): ProfileResponse{
            return profilesService.createProfile(request)
        }

    @GetMapping("/auth/users/v1/profiles/list")
    fun showProfiles() = profilesRepository.findAll()
}