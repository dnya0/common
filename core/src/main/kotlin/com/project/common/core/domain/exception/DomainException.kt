package com.project.common.core.domain.exception

class DomainException(
    val code: ErrorCode,
    override val message: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)
