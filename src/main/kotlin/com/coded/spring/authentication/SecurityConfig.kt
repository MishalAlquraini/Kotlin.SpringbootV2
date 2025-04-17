package com.coded.spring.authentication

import jdk.jfr.Enabled
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConf (
    private val userDetailsService: UserDetailsService
){

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http.csrf{ it.disable()}.authorizeHttpRequests {
//            it.requestMatchers("/users/v1/list").permitAll()
//                .anyRequest().authenticated()
//            it.requestMatchers("/users/v1/create/menu").permitAll()
//                .anyRequest().permitAll()
            it.requestMatchers("/Public/**").permitAll().requestMatchers("/users/v1/**")
                .authenticated()

        }
            .formLogin {
                it.defaultSuccessUrl("/welcome", true)}
            .userDetailsService(userDetailsService)
        return http.build()
    }
}