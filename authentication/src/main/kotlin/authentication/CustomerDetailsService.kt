package com.coded.spring.authentication

import com.coded.spring.users.UserEntity
import com.coded.spring.users.UsersRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class CustomerDetailsService(
    private val userRepo : UsersRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user: UserEntity = userRepo.findByUsername(username) ?:
        throw UsernameNotFoundException("User not found ...")

        return User.builder()
            .username(user.username)
            .password(user.password)
            .build()
    }

}