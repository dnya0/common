package com.project.common.core.presentation

import com.project.common.core.domain.exception.ErrorCode
import com.project.common.core.domain.exception.resolveMessage
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

sealed interface ApiResponse<out T> {
    val timestamp: LocalDateTime
    val code: String
    val message: String
}

data class Success<T>(
    override val timestamp: LocalDateTime = LocalDateTime.now(),
    override val code: String = HttpStatus.OK.name,
    override val message: String = "success",
    val data: T? = null
) : ApiResponse<T>

data class Error(
    override val timestamp: LocalDateTime = LocalDateTime.now(),
    val group: String,
    override val code: String,
    override val message: String
) : ApiResponse<Nothing>

fun <T> responseOf(data: T): ApiResponse<T> =
    Success(data = data)

fun <T> responseOf(message: String?, data: T): ApiResponse<T> =
    Success(message = message ?: "success", data = data)

fun <T> responseOf(
    status: HttpStatus,
    data: T? = null,
    message: String? = null,
): ApiResponse<T> =
    Success(
        code = status.value().toString(),
        message = message ?: status.reasonPhrase,
        data = data
    )

fun errorOf(code: ErrorCode, message: String?, ex: Throwable? = null): ApiResponse<Nothing> = Error(
    group = code.group,
    code = code.code.toString(),
    message = code.resolveMessage(message, ex)
)
