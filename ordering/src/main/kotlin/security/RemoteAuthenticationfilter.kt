package com.coded.spring.security

import com.coded.spring.client.AuthenticationClient
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.text.startsWith
import kotlin.text.substring


@Component
class RemoteAuthenticationFilter(
    private val authenticationClient: AuthenticationClient,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("Remote authentication filter running...")
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)
        val result = authenticationClient.checkToken(token)
        request.setAttribute("userId", result.userId)

        filterChain.doFilter(request, response)
    }
}