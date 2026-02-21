package com.project.common.core.support.log

import com.project.common.utils.log.logger
import java.time.Duration
import java.time.LocalDateTime

private val log = logger()

/**
 * Trailing Lambdas를 사용한 로그 함수
 */
fun <T> loggingStopWatch(function: () -> T): T {
    val startAt = LocalDateTime.now()
    log.info("Start At : $startAt")

    val result = function.invoke()

    val endAt = LocalDateTime.now()

    log.info("End At : $endAt")
    log.info("Logic Duration : ${Duration.between(startAt, endAt).toMillis()}ms")

    return result
}
