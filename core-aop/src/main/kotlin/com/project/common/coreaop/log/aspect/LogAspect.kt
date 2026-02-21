package com.project.common.coreaop.log.aspect

import com.project.common.coreaop.log.strategy.LogExecutionStrategy
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class LogAspect(private val strategy: LogExecutionStrategy) {

    @Suppress("EmptyFunctionBlock")
    @Pointcut("@within(com.project.common.coreaop.log.annotation.LogExternal)")
    fun classLoggingTarget() {
    }

    @Suppress("EmptyFunctionBlock")
    @Pointcut("@annotation(com.project.common.coreaop.log.annotation.LogExternal)")
    fun methodLoggingTarget() {
    }

    @Around("classLoggingTarget() || methodLoggingTarget()")
    fun around(joinPoint: ProceedingJoinPoint): Any? = strategy.execute(joinPoint)

}
