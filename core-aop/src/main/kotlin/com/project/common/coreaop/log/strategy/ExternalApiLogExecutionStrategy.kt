package com.project.common.coreaop.log.strategy

import com.project.common.coreaop.log.support.LogSanitizer
import com.project.common.coreaop.log.support.logExternalCall
import com.project.common.utils.log.loggerDelegate
import org.aspectj.lang.ProceedingJoinPoint

class ExternalApiLogExecutionStrategy(
    private val sanitizer: LogSanitizer
) : LogExecutionStrategy {

    private val log by loggerDelegate()

    override fun execute(joinPoint: ProceedingJoinPoint): Any? =
        logExternalCall(
            signature = joinPoint.signature.toShortString(),
            args = joinPoint.args,
            logger = log,
            sanitize = sanitizer::sanitize
        ) {
            joinPoint.proceed()
        }
}
