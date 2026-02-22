package com.project.common.redis.lock.exception

import com.project.common.core.domain.exception.ErrorCode
import org.springframework.http.HttpStatus

enum class LockErrorCode(
    override val code: Int,
    override val message: String,
    override val httpStatus: HttpStatus,
    override val group: String
) : ErrorCode {

    LOCK_ACQUISITION_FAILED(
        code = 1001,
        message = "락 획득에 실패했습니다.",
        httpStatus = HttpStatus.CONFLICT,
        group = "LOCK_001"
    ),

    LOCK_TIMEOUT(
        code = 1002,
        message = "락 대기 시간이 초과되었습니다.",
        httpStatus = HttpStatus.REQUEST_TIMEOUT,
        group = "LOCK_001"
    ),

    LOCK_ALREADY_HELD(
        code = 1003,
        message = "이미 다른 프로세스가 락을 보유 중입니다.",
        httpStatus = HttpStatus.CONFLICT,
        group = "LOCK_002"
    ),

    LOCK_RELEASE_FAILED(
        code = 1004,
        message = "락 해제에 실패했습니다.",
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        group = "LOCK_003"
    ),

    LOCK_EXPIRED(
        code = 1005,
        message = "락이 만료되었습니다.",
        httpStatus = HttpStatus.CONFLICT,
        group = "LOCK_004"
    ),

    INVALID_LOCK_KEY(
        code = 1006,
        message = "유효하지 않은 락 키입니다.",
        httpStatus = HttpStatus.BAD_REQUEST,
        group = "LOCK_005"
    ),

    LOCK_INTERRUPTED(
        code = 1007,
        message = "락 대기 중 인터럽트가 발생했습니다.",
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        group = "LOCK_006"
    )
}
