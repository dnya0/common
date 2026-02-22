package com.project.common.core.domain.exception

open class DomainException(
    val code: ErrorCode,
    override val message: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)
