package com.sapala.approveservice.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ExceptionHandler {
    @ResponseBody
    @ExceptionHandler
    fun handleException(e: CustomException): ResponseEntity<Any> {
        return ResponseEntity.status(e.status).body(e.body)
    }
}