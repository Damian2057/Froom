package com.froom.exception

import com.froom.exception.response.ErrorResponse
import com.froom.exception.type.InvalidCredentialsException
import com.froom.exception.type.TokenException
import com.froom.exception.type.UserNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors = HashMap<String, String>()

        ex.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage.orEmpty()
        }

        ex.bindingResult.globalErrors.forEach { error ->
            errors[error.objectName] = error.defaultMessage.orEmpty()
        }

        return ResponseEntity<Any>(
            ErrorResponse(
                message = "Invalid data provided",
                code = HttpStatus.BAD_REQUEST.value(),
                errors = errors
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    override fun handleExceptionInternal(
        ex: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return ResponseEntity<Any>(
            ErrorResponse(
                message = ex.message ?: "Unknown error",
                code = HttpStatus.BAD_REQUEST.value(),
                errors = emptyMap()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(TokenException::class,
        InvalidCredentialsException::class,
        UserNotFoundException::class)
    fun handleAuthenticationException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(
                message = e.message ?: "Unknown error",
                code = HttpStatus.UNAUTHORIZED.value(),
                errors = emptyMap()
            ),
            HttpStatus.UNAUTHORIZED
        )
    }
}