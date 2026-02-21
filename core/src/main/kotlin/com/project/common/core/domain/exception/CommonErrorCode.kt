package com.project.common.core.domain.exception

import org.springframework.http.HttpStatus

enum class CommonErrorCode(
    override val code: Int,
    override val message: String,
    override val httpStatus: HttpStatus,
    override val group: String
) : ErrorCode {

    INTERNAL_SERVER_ERROR(50000, "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR, "server"),
    INVALID_REQUEST(40000, "Invalid request", HttpStatus.BAD_REQUEST, "client");
}
