package org.example.parsingservice.handler

import org.example.parsingservice.domain.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HandlerMethodValidationException::class)
    fun handleHandlerMethodValidationException(ex: HandlerMethodValidationException): ResponseEntity<String> {
        return ResponseEntity(ex.detailMessageArguments.toList().toString(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(ex: MissingServletRequestParameterException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }
}