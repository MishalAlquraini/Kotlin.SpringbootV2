package com.coded.spring.authentication

import com.coded.spring.Application
import com.coded.spring.users.UserEntity
import com.coded.spring.users.UsersRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class InitUserRunner {

    @Bean
    fun initUser(
        usersRepository: UsersRepository,
        passwordEncoder: PasswordEncoder
    ) = CommandLineRunner {
        val user = UserEntity(
            name = "Meshal",
            age = 24,
            username = "meshal7",
            password = passwordEncoder.encode("Meshal99775283")
        )
        if (usersRepository.findByUsername(user.username) == null) {
            println("Creating user ${user.username}")
            usersRepository.save(user)
        } else {
            println("User ${user.username} #${user.id} already exists")

//        }
//        try{
//            userRepository.findByUsername(user.username)
//        }
//        catch (){

        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args).close()
}