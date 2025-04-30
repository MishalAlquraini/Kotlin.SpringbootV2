package com.coded.spring.users

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    val usersRepository: UsersRepository,
    val usersService: UsersService,
    @Value("\${server-welcome-message}")
    val companyMessage: String,
){

    @GetMapping("/welcome")
    fun welcome() =  "Welcome to Online Ordering by $companyMessage"


    @GetMapping("/users/v1/list")
    fun sayUsers() = usersRepository.findAll()


    @PostMapping("/users/v1/create")
    fun createUser(@RequestBody request: AddUserRequest):Any{
        return try {
            usersService.createUser(request)
            ResponseEntity.ok("user is good to go")
        } catch (e: IllegalArgumentException){
            ResponseEntity.badRequest().body(e.message)
        }


    }

}

