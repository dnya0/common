package com.project.common.core.presentation

import com.project.common.core.domain.exception.ErrorCode
import com.project.common.core.domain.exception.resolveMessage
import org.springframework.http.HttpStatus

sealed interface ApiResponse<out T> {
    val code: String
    val message: String
}

data class Success<T>(
    override val code: String = HttpStatus.OK.toString(),
    override val message: String = "success",
    val data: T
) : ApiResponse<T>

data class Error(
    val group: String,
    override val code: String,
    override val message: String
) : ApiResponse<Nothing>

fun <T> responseOf(data: T): ApiResponse<T> =
    Success(data = data)

fun <T> responseOf(message: String?, data: T): ApiResponse<T> =
    Success(message = message ?: "success", data = data)

fun errorOf(code: ErrorCode, message: String?, ex: Throwable? = null): ApiResponse<Nothing> = Error(
    group = code.group,
    code = code.code.toString(),
    message = code.resolveMessage(message, ex)
)
