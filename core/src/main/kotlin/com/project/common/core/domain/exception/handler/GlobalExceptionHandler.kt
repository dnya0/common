package com.project.common.core.domain.exception.handler

import com.project.common.core.domain.exception.CommonErrorCode
import com.project.common.core.domain.exception.DomainException
import com.project.common.core.domain.exception.ErrorCode
import com.project.common.core.presentation.ApiResponse
import com.project.common.core.presentation.errorOf
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DomainException::class)
    fun handleBusiness(ex: DomainException): ResponseEntity<ApiResponse<Nothing>> = ex.code.toResponse(ex)

    @ExceptionHandler(Exception::class)
    fun handleUnknown(ex: Exception): ResponseEntity<ApiResponse<Nothing>> = ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(errorOf(CommonErrorCode.INTERNAL_SERVER_ERROR, "Unexpected error"))

    private fun ErrorCode.toResponse(ex: Throwable): ResponseEntity<ApiResponse<Nothing>> = ResponseEntity
        .status(this.httpStatus)
        .body(errorOf(this, ex.message))
}
