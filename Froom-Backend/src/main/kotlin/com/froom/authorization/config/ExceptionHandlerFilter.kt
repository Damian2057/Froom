package com.froom.authorization.config

import com.froom.authorization.util.toStringJSON
import com.froom.exception.response.ErrorResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.multipart.MultipartException
import java.io.IOException

class ExceptionHandlerFilter : OncePerRequestFilter() {

    @Throws(ServletException::class,
        IOException::class,
        MultipartException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: RuntimeException) {
            val errorResponse = ErrorResponse(
                message = e.message ?: "Unknown error",
                code = HttpStatus.FORBIDDEN.value(),
                errors = emptyMap()
            )
            response.status = HttpStatus.FORBIDDEN.value()
            response.writer.write(errorResponse.toStringJSON())
        }
    }
}
