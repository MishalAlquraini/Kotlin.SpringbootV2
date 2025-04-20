package com.coded.spring.Profiles

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(
    val profilesRepository: ProfilesRepository,
    val profilesService: ProfilesService
){
    @PostMapping("/users/v1/profiles")
        fun createProfiles(@RequestBody request: ProfileRequest): ProfileResponse{
            return profilesService.createProfile(request)
        }

}