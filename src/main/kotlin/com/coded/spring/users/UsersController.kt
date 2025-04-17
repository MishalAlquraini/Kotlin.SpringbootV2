package com.coded.spring.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    val usersRepository: UsersRepository,
    val usersService: UsersService
){

    @GetMapping("/welcome")
    fun welcome() =  "Get in yoo"


    @GetMapping("/users/v1/list")
    fun sayUsers() = usersRepository.findAll()


    @PostMapping("/users/v1/create")
    fun createUser(@RequestBody request: AddUserRequest):Any{
        if (isValidPassword(request.password)){
            val newUser = usersService.createUser(request)
            return newUser
        }
        return "your password is invalid"


    }

}

