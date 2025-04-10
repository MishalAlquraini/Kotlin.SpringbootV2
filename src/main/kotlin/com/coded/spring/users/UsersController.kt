package com.coded.spring.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class UsersController(
    val usersRepository: UsersRepository
){

    @GetMapping("/users/v1/list")
    fun sayUsers() = usersRepository.findAll()

}

