package com.coded.spring.users

import jakarta.inject.Named
import org.springframework.security.crypto.password.PasswordEncoder

@Named
class UsersService(
    private val usersRepository: UsersRepository,
    private val encoder: PasswordEncoder,
) {

    fun createUser(request: AddUserRequest): addedUserResponse{
        val myNewUserEntity = UserEntity(
            name = request.name,
            age = request.age,
            username = request.username,
            password = encoder.encode(request.password),
        )
        if (request.username.isBlank()){
            throw IllegalArgumentException("username not correct")}

        val savedUser=  usersRepository.save(myNewUserEntity)

        return addedUserResponse(savedUser.id,savedUser.username)
    }

    fun listUsers(): List<User> = usersRepository.findAll().map {
        User(
            name = it.name,
            age = it.age
        )
    }

}


fun isValidPassword(password: String): Boolean {

    return password.length >= 6 && password.any { it.isDigit() } && password.any { it.isUpperCase() }
}


data class AddUserRequest(
    val name: String,
    val username: String,
    val password: String,
    val age: Int
)



data class addedUserResponse(
    val id: Long?,
    val username: String
)

data class User(
    val name: String,
    val age: Int
)