package com.project.common.coreaop.log.strategy

import org.aspectj.lang.ProceedingJoinPoint

interface LogExecutionStrategy {

    fun execute(joinPoint: ProceedingJoinPoint): Any?

}
