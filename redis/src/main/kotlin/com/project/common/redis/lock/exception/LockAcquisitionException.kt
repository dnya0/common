package com.project.common.redis.lock.exception

import com.project.common.core.domain.exception.DomainException
import com.project.common.redis.lock.exception.LockErrorCode.LOCK_ACQUISITION_FAILED

class LockAcquisitionException(
    lockKey: String,
    timeoutMillis: Long,
    override val message: String = "Failed to acquire lock for key [$lockKey] within $timeoutMillis ms.",
    cause: Throwable? = null
) : DomainException(LOCK_ACQUISITION_FAILED, message, cause)
