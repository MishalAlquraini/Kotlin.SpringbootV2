package com.coded.spring

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
@Order(1) // to select this filter order in referral to other filters
class LoggingFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cachedRequest = ContentCachingRequestWrapper(request)
        val cashedResponse = ContentCachingResponseWrapper(response)
        filterChain.doFilter(cachedRequest,cashedResponse)
        cashedResponse.copyBodyToResponse()

        val requestBody = String(cachedRequest.contentAsByteArray)
        logger.info("Request: method=${request.method}, url=${request.requestURI}, body ${requestBody}")

        val responseBody = String(cashedResponse.contentAsByteArray)
        logger.info("Request: status=${response.status}, body=$responseBody")

        cashedResponse.copyBodyToResponse()
    }

}