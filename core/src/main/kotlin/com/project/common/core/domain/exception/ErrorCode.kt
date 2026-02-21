package com.project.common.core.domain.exception

import org.springframework.http.HttpStatus

interface ErrorCode {
    val group: String
    val message: String
    val code: Int
    val httpStatus: HttpStatus
}

fun ErrorCode.resolveMessage(custom: String?, e: Throwable? = null): String =
    custom
        ?: e?.message?.let { "$message - $it" }
        ?: message
